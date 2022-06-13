package org.cis120;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JPanel;
import javax.swing.*;

public class GamePlay extends JPanel {

    public static final int WIDTH = 600;
    public static final int LENGTH = 600;
    public static final int INTERVAL = 240;
    private Map<Integer, Coordinates> numOfGrid = new TreeMap<>();
    private LinkedList<Coordinates> coordinates = new LinkedList<>();
    private Snake snake = new Snake(240, 240);
    private final JLabel status;
    private boolean started;
    private Apple apple = new Apple(80, 320);
    private Bomb bomb = new Bomb(120, 80, apple, snake, 9.5);
    private int state;

    GamePlay(JLabel status) {
        //begins at start screen
        started = false;
        state = 0;

        //draws grid
        int count = 0;
        for (int i = 0; i < WIDTH; i = i + 40) {
            for (int j = 0; j < LENGTH; j = j + 40) {
                Coordinates a = new Coordinates(i, j);
                coordinates.add(a);
                numOfGrid.put(count, a);
                count++;
            }
        }

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.setDirection("LEFT");
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setDirection("RIGHT");
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.setDirection("DOWN");
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    snake.setDirection("UP");
                }
            }
        });

        javax.swing.Timer timer = new Timer(INTERVAL, e -> rules());
        timer.start(); // MAKE SURE TO START THE TIMER!

        this.setFocusable(true);
        this.status = status;

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // if not start screen play game
        if (state != 0) {
            for (Coordinates c : coordinates) {
                c.drawGrid(g);
            }
            apple.drawShape(g);
            bomb.drawShape(g);
            snake.paintComponent(g);

            endGame(g);
        } else {
            startScreen(g);
        }

    }

    public void rules() {
        if (started && state != 0) {
            snake.move();
            // if snake collides with apple, grow, move, increment score
            if (snake.appleCollision(apple)) {
                snake.grow();
                moveApple();
                status.setText("Score: " + snake.getScore());
            }
          // if snake collides with bomb, grow, shrink, decrease score
            if (snake.bombCollision(bomb)) {
                snake.shrink();
                bomb.changePosition();
                status.setText("Score: " + snake.getScore());
            }
        }

        repaint();
    }

    public void moveApple() {

        Collection<Coordinates> body = snake.snakeValues();
        int tile = 1 + (int) (Math.random() * 100);
        Coordinates newCord = coordinates.get(tile);
        //make sure apple isn't on corners or in the snakes body
        for (Coordinates c : body) {
            while (c.getyPos() == newCord.getyPos() && c.getxPos() == newCord.getxPos()
                    || tile == 0 || tile == 9 || tile == 90) {
                tile = 1 + (int) (Math.random() * 100);
                newCord = coordinates.get(tile);
            }
        }
        apple.setxPos(newCord.getxPos());
        apple.setyPos(newCord.getyPos());
    }

    public void endGame(Graphics g) {
        if (!snake.checkHead()) {
            started = false;
            g.setColor(Color.cyan);
            g.fillRect(0, 0, 600, 600);
            g.setColor(Color.black);
            g.drawString("You Lose", 300, 300);

        }
    }

    public void startScreen(Graphics g) {
        if (state == 0) {
            g.setColor(Color.pink);
            g.fillRect(0, 0, 600, 600);
            g.setColor(Color.black);
            g.drawString("Welcome Nature Keepers!", 100, 100);
            g.drawString("Today you will be working in the Snake Exhibit!", 100, 125);
            g.drawString("(Have no Fear, Taylor is a nice gal!)", 100, 150);
            g.drawString("Use the arrow keys (UP, DOWN, LEFT, RIGHT) to feed her", 100, 175);
            g.drawString("the apples highlighted in red!", 100, 200);
            g.drawString("Beware of the parasites she mistakes for food in black!", 100, 225);
            g.drawString("Get her as Fat as possible, if she withers away she dies!", 100, 250);
            g.drawString("Don't run into yourself, and stay away from the walls!", 100, 275);
            g.drawString("press play to begin!", 100, 300);
            g.drawString(" - love Tash!", 100, 325);
        }
    }

    public void setState(int a) {
        state = a;
        requestFocusInWindow();
    }

    public void reset() {
        started = true;
        apple = new Apple(280, 320);
        snake = new Snake(240, 240);
        bomb = new Bomb(240, 200, apple, snake, 9.5);
        status.setText("Score: " + snake.getScore());
        requestFocusInWindow();

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, LENGTH);
    }

}