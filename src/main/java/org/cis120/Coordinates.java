package org.cis120;

import java.awt.*;

public class Coordinates {
    private int xPos;
    private int yPos;
    private boolean apple;

    Coordinates(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        apple = false;
    }

    public void drawGrid(Graphics g) {

        if (!apple) {
            g.drawRect(xPos, yPos, 40, 40);
        } else {
            g.setColor(Color.RED);
            g.fillRect(xPos, yPos, 40, 40);
        }
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

}
