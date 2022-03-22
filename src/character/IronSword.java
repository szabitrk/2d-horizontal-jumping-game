package character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class IronSword extends Clothing {

    private BufferedImage img;
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

    public IronSword(Orc o) {
        super(o);
    }

    private void addIronSword(Graphics g) {
        try {
            img = ImageIO.read(new File("resources/iron_sword.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(img, posX, posY, null);
    }

    @Override
    public void draw(Graphics g) {
        o.setPosX(posX);
        o.setPosY(posY);
        super.draw(g);
        addIronSword(g);
    }
}

