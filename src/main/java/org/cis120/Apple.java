package org.cis120;

import java.awt.*;

public class Apple {
    private int xPos;
    private int yPos;

    Apple(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void drawShape(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xPos, yPos, 40, 40);
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int x) {
        this.xPos = x;
    }

    public void setyPos(int y) {
        this.yPos = y;
    }

}
