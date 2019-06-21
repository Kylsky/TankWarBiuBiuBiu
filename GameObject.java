package tank.Day8;

import java.awt.*;

public abstract class GameObject {
    protected int x,y;      //物体位置
//    public Rectangle rect = new Rectangle();
    public abstract void paint(Graphics graphics);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract int getWIDTH();

    public abstract int getHEIGHT();
}
