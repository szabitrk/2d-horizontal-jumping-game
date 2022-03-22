package gameplay;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//template for every platform type object
//contains x,y coordinates, width, height and a buffered image
public class Platform {

    private BufferedImage platformImg;
    private int platformPosX;
    private int platformPosY;
    private final int platformWidth;

    public Platform() {
        try {
            platformImg = ImageIO.read(new File("resources/plfatform3.png")); //100x100
        } catch (IOException e) {
            e.printStackTrace();
        }
        platformWidth = 138;//199;
        platformPosX = 0;
        platformPosY = 0;
    }

    public int getPosX() {
        return platformPosX;
    }

    public void setPosX(int posX) {
        this.platformPosX = posX;
    }

    public int getPosY() {
        return platformPosY;
    }

    public void setPosY(int posY) {
        this.platformPosY = posY;
    }

    public int getPlatformWidth() {
        return platformWidth;
    }

    public BufferedImage getPlatformImg() {
        return platformImg;
    }
}
