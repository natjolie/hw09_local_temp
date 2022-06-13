package org.cis120;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Snake extends JComponent {
    private int initX;
    private int initY;
    private TreeMap<Integer, Coordinates> body = new TreeMap<>();
    private String direction;

    public Snake(int initX, int initY) {
        this.initX = initX;
        this.initY = initY;
        Coordinates c = new Coordinates(initX, initY);
        body.put(0, c);
        body.put(1, new Coordinates(initX, initY + 40));
        body.put(2, new Coordinates(initX, initY + 80));

    }

    public void move() {
        // the previous value now equals the coordinate following
        if (direction != null) {
            Coordinates[] c = body.values().toArray(new Coordinates[0]);
            for (int i = 1; i < c.length; i++) {
                Coordinates prev = c[i - 1];
                body.put(i, new Coordinates(prev.getxPos(), prev.getyPos()));
            }

            if (direction.equals("LEFT")) {
                body.put(0, new Coordinates(body.get(0).getxPos() - 40, body.get(0).getyPos()));
            }
            if (direction.equals("RIGHT")) {
                body.put(0, new Coordinates(body.get(0).getxPos() + 40, body.get(0).getyPos()));
            }
            if (direction.equals("UP")) {
                body.put(0, new Coordinates(body.get(0).getxPos(), body.get(0).getyPos() - 40));
            }
            if (direction.equals("DOWN")) {
                body.put(0, new Coordinates(body.get(0).getxPos(), body.get(0).getyPos() + 40));
            }
        }
    }

    public void setDirection(String c) {
        this.direction = c;

    }

    public void paintComponent(Graphics g) {
        for (Coordinates c : body.values()) {
            g.setColor(Color.BLUE);
            g.fillRect(c.getxPos(), c.getyPos(), 40, 40);
        }
    }

    public boolean checkHead() {
        //snake can't be 0
        if (body.size() == 0) {
            return false;
        }
        //out of bounds
        int x = body.get(0).getxPos();
        int y = body.get(0).getyPos();
        if (x < 0 || x > 600) {
            return false;
        }

        if (y < 0 || y > 600) {
            return false;
        }

        Coordinates[] c = body.values().toArray(new Coordinates[0]);
        //snake cannot collide with itself
        for (int i = 1; i < body.size(); i++) {
            if (c[0].getxPos() == c[i].getxPos() && c[0].getyPos() == c[i].getyPos()) {
                return false;
            }
        }
        return true;
    }

    public boolean appleCollision(Apple a) {
        int x = body.get(0).getxPos();
        int y = body.get(0).getyPos();
        if (a.getxPos() == x && a.getyPos() == y) {
            return true;
        }
        return false;
    }

    public int getScore() {
        return body.size();
    }

    public boolean bombCollision(Bomb b) {
        int x = body.get(0).getxPos();
        int y = body.get(0).getyPos();
        if (b.getxPos() == x && b.getyPos() == y) {
            return true;
        }
        return false;

    }
   //have to initialize a new map, to remove the last item
    public void shrink() {
        if (body != null) {
            TreeMap<Integer, Coordinates> a = new TreeMap<>();
            for (int i = 0; i < body.size() - 1; i++) {
                a.put(i, body.get(i));
            }
            body = a;
        }
    }

    public void addForTest(int a, Coordinates c) {
        body.put(a, c);
    }

    public Collection<Coordinates> snakeValues() {
        return body.values();
    }

    public void grow() {
        Coordinates first = body.get(0);
        Collection<Coordinates> c = body.values();

        if (direction != null) {
            for (int i = body.size() - 1; i >= 0; i--) {
                Coordinates c1 = body.get(i);
                body.put(i + 1, c1);
            }
            if (direction.equals("LEFT")) {
                body.put(0, new Coordinates(first.getxPos() - 40, first.getyPos()));
            }
            if (direction.equals("RIGHT")) {
                body.put(0, new Coordinates(first.getxPos() + 40, first.getyPos()));
            }
            if (direction.equals("UP")) {
                body.put(0, new Coordinates(first.getxPos(), first.getyPos() - 40));
            }
            if (direction.equals("DOWN")) {
                body.put(0, new Coordinates(first.getxPos(), first.getyPos() + 40));
            }
        }
    }

}
