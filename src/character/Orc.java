package character;

import java.awt.*;

public interface Orc {
    void draw(Graphics g);

    void setPosX(int newPosX);

    void setPosY(int newPosY);

    int getPosX();

    int getPosY();
}
