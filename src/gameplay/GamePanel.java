package gameplay;

import character.CharacterInitializePanel;
import character.Orc;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel {

    List<Platform> platformList = new CopyOnWriteArrayList<>();
    private int charPosX;
    private int charPosY;
    private final int frameWidth;
    private final int frameHeight;
    int charWidth = 150;
    int charHeight = 150;
    CharacterInitializePanel initPanel;

    private Orc creature = null;

    public GamePanel(int frameWidth, int frameHeight, CharacterInitializePanel initPanel) {
        this.initPanel = initPanel;
        this.frameHeight = frameHeight;
        this.frameWidth = frameWidth;

        //initializing platforms
        int numOfPlatforms = 10;
        for (int i = 0; i < numOfPlatforms; i++) {
            platformList.add(initializePlatform());
        }

        charPosX = platformList.get(0).getPosX() + charWidth / 3;
        charPosY = platformList.get(0).getPosY() - charHeight;

        setVisible(true);
    }

    protected Platform initializePlatform() {

        Random rd = new Random();
        Platform temp = new Platform();
        if (platformList.size() == 0)
            temp.setPosY(frameHeight - 2 * charHeight);
        else
            temp.setPosY(platformList.get(platformList.size() - 1).getPosY() - 160);

        temp.setPosX(rd.nextInt(frameWidth - temp.getPlatformWidth() * 3 / 2));
        return temp;
    }

    public int getCharHeight() {
        return charHeight;
    }

    public int getCharWidth() {
        return charWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setCharPosX(int newX) {
        if (newX < frameWidth - charWidth && newX > 0) {
            creature.setPosX(newX);
            charPosX = newX;
        }
    }

    public void setCharPosY(int newY) {
        if (frameHeight - newY >= 0) {
            creature.setPosY(newY);
            charPosY = newY;
        }
    }

    public int getCharPosY() {
        return charPosY;
    }

    public int getCharPosX() {
        return charPosX;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        if (creature == null) {
            creature = initPanel.getCreature();
            creature.setPosX(charPosX);
            creature.setPosY(charPosY);
        }
        creature.draw(g2);

        Vector<Platform> temp = new Vector<>(platformList);
        for (Platform p : temp) {
            g2.drawImage(p.getPlatformImg(), p.getPosX(), p.getPosY(), this);
        }
    }
}