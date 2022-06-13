package org.cis120;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void outOfBoundsRight() {
        Snake s = new Snake(600, 120);
        s.setDirection("RIGHT");
        s.move();
        assertFalse(s.checkHead(), "Outofbounds");
    }

    @Test
    public void outOfBoundsLeft() {
        Snake s = new Snake(0, 120);
        s.setDirection("LEFT");
        s.move();
        assertFalse(s.checkHead(), "Outofbounds");
    }

    @Test
    public void outOfBoundsUp() {
        Snake s = new Snake(120, 0);
        s.setDirection("UP");
        s.move();
        assertFalse(s.checkHead(), "Outofbounds");
    }

    @Test
    public void outOfBoundsDown() {
        Snake s = new Snake(120, 560);
        s.setDirection("Down");
        s.move();
        assertFalse(s.checkHead(), "Outofbounds");
    }

    @Test
    public void crashIntoBody() {
        Snake s = new Snake(120, 120);
        s.addForTest(3, new Coordinates(120, 240));
        s.addForTest(4, new Coordinates(120, 280));
        s.setDirection("LEFT");
        s.move();
        s.setDirection("DOWN");
        s.move();
        s.setDirection("RIGHT");
        s.move();
        assertFalse(s.checkHead(), "Collision with Body");
    }

    @Test
    public void testShrink() {
        Snake s = new Snake(120, 120);
        Apple a = new Apple(300, 300);
        Bomb b = new Bomb(120, 80, a, s, 1);
        b.setXposition(120);
        b.setYposition(80);
        s.setDirection("UP");
        s.move();
        assertEquals(80, b.getyPos(), "Collision with Body");
    }

    @Test
    public void nullSnake() {
        Snake s = new Snake(120, 120);
        s.shrink();
        s.shrink();
        s.shrink();
        assertFalse(s.checkHead(), "null");
    }

    @Test
    public void testBombCollision() {
        Apple apple = new Apple(280, 320);
        Snake snake = new Snake(240, 240);
        Bomb bomb = new Bomb(240, 200, apple, snake, 10);
        snake.setDirection("UP");
        snake.move();
        assertTrue(snake.bombCollision(bomb), "Collision with Body");
    }

    @Test
    public void testAppleCollision() {
        Apple apple = new Apple(240, 200);
        Snake snake = new Snake(240, 240);
        snake.setDirection("UP");
        snake.move();
        assertTrue(snake.appleCollision(apple), "Collision with Body");
    }

    @Test
    public void testBombPosition() {
        Apple apple = new Apple(280, 320);
        Snake snake = new Snake(240, 240);
        Bomb bomb = new Bomb(240, 200, apple, snake, 10);
        snake.setDirection("UP");
        snake.move();
        assertTrue(snake.bombCollision(bomb), "Collision with Body");
    }

    @Test
    public void grow() {
        Apple apple = new Apple(280, 320);
        Snake snake = new Snake(240, 240);
        snake.setDirection("UP");
        snake.grow();
        snake.grow();
        snake.grow();
        snake.grow();
        snake.grow();
        assertEquals(8, snake.getScore(), "Collision with Body");
    }

    @Test
    public void shrink() {
        Apple apple = new Apple(280, 320);
        Snake snake = new Snake(240, 240);
        Bomb bomb = new Bomb(240, 200, apple, snake, 10);
        snake.setDirection("UP");
        snake.grow();
        snake.grow();
        snake.grow();
        snake.grow();
        snake.grow();
        snake.shrink();
        snake.shrink();
        assertEquals(6, snake.getScore(), "Collision with Body");
    }

    @Test
    public void randomization() {
        Apple apple = new Apple(280, 320);
        Snake snake = new Snake(240, 240);
        Bomb bomb = new Bomb(240, 200, apple, snake, 10);
        bomb.changePosition();
        assertFalse(apple.getxPos() == bomb.getxPos() && apple.getyPos() == bomb.getyPos());

    }


}
