package character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OrcCreature implements Orc {

    private BufferedImage img = null;
    private int posX = 0;
    private int posY = 0;

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public void draw(Graphics g) {
        try {
            img = ImageIO.read(new File("resources/orc.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(img, posX, posY, null);
    }
}
