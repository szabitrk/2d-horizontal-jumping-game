package gameplay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//is relevant when the character misses a jump and falls
//gives the player to option to play again or exit
public class GameOverPanel extends JPanel {

    Font arcadeFont;
    JLabel score2;
    String scoreValue;

    public void setScoreValue(String scoreValue) {
        this.scoreValue = scoreValue;
        score2.setText(scoreValue);
    }

    public GameOverPanel(MyFrame frame) {

        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/ARCADE_N.ttf")).deriveFont(22f);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/ARCADE_N.ttf")));

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel("GAME OVER!");
        JLabel score1 = new JLabel("SCORE:");
        score2 = new JLabel(scoreValue);
        JPanel score = new JPanel();
        JPanel btns = new JPanel();

        JButton exit = new JButton("Exit!");
        JButton again = new JButton("Play again!");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        score.setLayout(new BoxLayout(score, BoxLayout.X_AXIS));
        btns.setLayout(new BoxLayout(btns, BoxLayout.X_AXIS));

        label.setFont(arcadeFont);
        score1.setFont(arcadeFont);
        score2.setFont(arcadeFont);

        score.add(score1);
        score.add(score2);

        btns.add(exit);
        btns.add(again);

        this.setOpaque(false);
        score.setOpaque(false);
        btns.setOpaque(false);

        exit.addActionListener(e -> System.exit(0));

        //starts new game and disposes old one
        again.addActionListener(e -> {
            Thread restartThread = new Thread(MyFrame::new);
            restartThread.start();
            frame.dispose();
        });

        label.setBorder(new EmptyBorder(100, 0, 0, 190));
        score.setBorder(new EmptyBorder(0, 200, 0, 0));
        btns.setBorder(new EmptyBorder(200, 200, 0, 0));

        add(label);
        add(score);
        add(btns);
    }
}
