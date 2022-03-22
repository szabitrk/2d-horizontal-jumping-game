package gameplay;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//controls the gameplay:
//->movements of the character with key listeners: space = jump, {a, left arrow key} = move left, {d, right arrow key} = move right
//->descending of the platforms in order to keep the character more or less in the middle of the frame
//->when the character falls initializes game over panel where the player has the option to play again or exit
public class ControlPanel extends JPanel {

    private boolean gameOver = false;
    private boolean alreadyFalling = false;
    private boolean jumping = false;
    private boolean moveLeftPressed = false;
    private boolean moveRightPressed = false;

    private final int frameHeight;
    private int score;
    private final CardLayout crd;
    private final JPanel cardContainer;
    private final GameOverPanel gameOverPanel;
    private final ScorePanel scorePanel;
    private final GamePanel gamePanel;

    public ControlPanel(GamePanel gamePanel, ScorePanel scorePanel, JPanel cardContainer, CardLayout crd, GameOverPanel gameOverPanel) {

        this.scorePanel = scorePanel;
        this.cardContainer = cardContainer;
        this.crd = crd;
        this.gameOverPanel = gameOverPanel;
        this.gamePanel = gamePanel;
        this.frameHeight = gamePanel.getFrameHeight();

        score = 0;

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 32 || e.getKeyCode() == 87 || e.getKeyCode() == 38) {
                    if (!jumping && !alreadyFalling) {
                        jumping = true;
                        Thread jumpThread = new Thread(new JumpingRunnable());
                        jumpThread.start();
                    }
                } else if ((e.getKeyCode() == 65 || e.getKeyCode() == 37) && !moveLeftPressed ) {
                    moveLeftPressed = true;
                    Thread moveLeftThread = new Thread(new MoveLeftRunnable());
                    moveLeftThread.start();
                } else if ((e.getKeyCode() == 68 || e.getKeyCode() == 39) && !moveRightPressed ) {
                    moveRightPressed = true;
                    Thread moveRightThread = new Thread(new MoveRightRunnable());
                    moveRightThread.start();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
                    moveLeftPressed = false;
                } else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
                    moveRightPressed = false;
                }
            }
        });
    }

    private void gameOver() {
        gameOver = true;
        gameOverPanel.setScoreValue(Integer.toString(scorePanel.getScore()));
        crd.next(cardContainer);
    }

    private boolean checkIfOnPlatform() {

        int charRangeA = gamePanel.getCharPosX() + 50;
        int charRangeB = charRangeA + gamePanel.getCharWidth() - 100;

        try {
            List<Platform> platforms = gamePanel.getPlatformList();

            //checking every platform coordinates while falling
            for (Platform platform : platforms) {
                int platRangeA = platform.getPosX();
                int platRangeB = platRangeA + platform.getPlatformWidth();
                if (Math.abs(gamePanel.getCharPosY() + gamePanel.getCharHeight() - platform.getPosY()) < 3
                        && charRangeB > platRangeA
                        && charRangeA < platRangeB
                ) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private class JumpingRunnable implements Runnable {

        @Override
        public void run() {

            Random rd = new Random();

            //1/10 chance of grunting before jumping
            if (rd.nextInt() % 10 == 0)
                playSound("resources/jump_sound.wav");

            for (int i = 0; i < 200; i++) {

                //if character goes past 2/3 of frame, lower all platforms instead of jumping
                if (gamePanel.getCharPosY() <= frameHeight / 3) {
                    for (int j = gamePanel.getPlatformList().size() - 1; j >= 0; j--) {
                        Platform platform = gamePanel.getPlatformList().get(j);
                        platform.setPosY(platform.getPosY() + 1);
                        if (platform.getPosY() >= frameHeight) {
                            gamePanel.getPlatformList().remove(platform);
                            gamePanel.getPlatformList().add(gamePanel.initializePlatform());
                        }
                    }
                } else {
                    gamePanel.setCharPosY(gamePanel.getCharPosY() - 1);
                }

                gamePanel.repaint();
                score++;
                if (score > scorePanel.getScore())
                    scorePanel.setScore(score);

                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            jumping = false;
            Thread fallingThread = new Thread(new FallingRunnable());
            fallingThread.start();
        }
    }

    private class FallingRunnable implements Runnable {

        @Override
        public void run() {

            alreadyFalling = true;
            boolean stopFalling = false;
            int i = 0;
            while (!stopFalling) {
                stopFalling = checkIfOnPlatform();
                gamePanel.setCharPosY(gamePanel.getCharPosY() + 1);
                gamePanel.repaint();
                score--;

                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if (gamePanel.getCharPosY() >= frameHeight - 200 && !gameOver) {
                    gameOver();
                    stopFalling = true;
                }
                i++;

                if (i == 200)
                    playSound("resources/fall_sound.wav");
            }
            alreadyFalling = false;
        }
    }

    private class MoveLeftRunnable implements Runnable {

        @Override
        public void run() {

            do {
                gamePanel.setCharPosX(gamePanel.getCharPosX() - 3);
                gamePanel.repaint();

                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if (!checkIfOnPlatform() && !alreadyFalling && !jumping && !gameOver) {
                    Thread fallingThread = new Thread(new FallingRunnable());
                    fallingThread.start();
                }


            } while (moveLeftPressed);
        }
    }

    private class MoveRightRunnable implements Runnable {

        @Override
        public void run() {

            do {
                gamePanel.setCharPosX(gamePanel.getCharPosX() + 3);
                gamePanel.repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                if (!checkIfOnPlatform() && !alreadyFalling && !jumping && !gameOver) {
                    Thread fallingThread = new Thread(new FallingRunnable());
                    fallingThread.start();
                }

            } while (moveRightPressed);
        }
    }

    private void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        }
    }
}



