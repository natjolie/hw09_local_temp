package org.cis120;

import java.awt.*;
import java.util.Collection;

public class Bomb extends Apple {
    private int xPos;
    private int yPos;
    private Apple apple;
    private Snake snake;
    private double time;

    public Bomb(int x, int y, Apple a, Snake s, double time) {
        super(x, y);
        this.xPos = x;
        this.yPos = y;
        apple = a;
        snake = s;
        this.time = time;
    }

    public void changePosition() {
        int randomX = 40 * (int) (15 * Math.random());
        int randomY = 40 * (int) (15 * Math.random());

        Collection<Coordinates> body = snake.snakeValues();
        //checks to make sure its not initialized to the body or the apple
        for (Coordinates c : body) {
            while ((c.getyPos() == randomY && c.getxPos() == randomX) ||
                    (apple.getyPos() == randomY && apple.getxPos() == randomX)) {
                randomX = 40 * (int) (15 * Math.random());
                randomY = 40 * (int) (15 * Math.random());
            }
        }
        xPos = randomX;
        yPos = randomY;
    }

    @Override
    public void drawShape(Graphics g) {
        double a = Math.random() * 10;
        if (a < time) {
            g.setColor(Color.BLACK);
            g.fillRect(xPos, yPos, 40, 40);
        } else {
            changePosition();
        }
    }

    @Override
    public int getxPos() {
        return xPos;
    }

    @Override
    public int getyPos() {
        return yPos;
    }

    public void setXposition(int x) {
        xPos = x;
    }

    public void setYposition(int y) {
        yPos = y;
    }
}
