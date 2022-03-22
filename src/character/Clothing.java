package character;

import java.awt.*;

public abstract class Clothing implements Orc {
    protected Orc o;
    private int posX;
    private int posY;

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public void setPosX(int posX) {
        this.posX = posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Clothing(Orc o) {
        posX = o.getPosX();
        posY = o.getPosY();
        this.o = o;
    }

    public void draw(Graphics g) {
        posX = o.getPosX();
        posY = o.getPosY();
        o.draw(g);
    }
}
