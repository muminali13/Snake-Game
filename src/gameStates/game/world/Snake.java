package gameStates.game.world;

import handlers.Images;
import input.KeyboardHandler;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Snake {

    private final World world;

    enum Direction {
        LEFT (-1), RIGHT (1), UP (-World.WIDTH), DOWN (World.WIDTH);

        int change;
        Direction(int change) {
            this.change = change;
        }
    }

    private Direction currentDirection, nextDirection;

    private boolean ignoreInput = false;
    private boolean dead = false;

    private int update = 0;
    private int speed = 5;

    private int snakeHead;
    private final LinkedList<Integer> snakeBody;


    public Snake(World world, int snakeHead, int length) {

        this.world = world;
        this.snakeHead = snakeHead;
        this.snakeBody = new LinkedList<>();

        for (int i = 1; i < length; i++) {
            snakeBody.addLast(snakeHead-i);
        }
        currentDirection = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
    }

    public Snake(World world) {

        this.world = world;
        int length = 3 + (int) (Math.random() * 9);

        int x = length + (int) (Math.random() * (World.WIDTH - 2 * length));
        int y = length + (int) (Math.random() * (World.HEIGHT - 2 * length));

        this.snakeHead = x + y * World.WIDTH;

        this.snakeBody = new LinkedList<>();

        currentDirection = nextDirection = randomDirection();

        for (int i = 1; i < length; i++) {
            snakeBody.addLast(snakeHead - (i * currentDirection.change));
        }
    }

    public void update() {

        // check input
        if (!ignoreInput) {

            if (KeyboardHandler.isKeyPressed(KeyboardHandler.KEY_MOVE_RIGHT)) {
                switch (currentDirection) {
                    case UP:
                    case DOWN:
                        nextDirection = Direction.RIGHT;
                        break;
                }
            }
            else if (KeyboardHandler.isKeyPressed(KeyboardHandler.KEY_MOVE_LEFT)) {
                switch (currentDirection) {
                    case UP:
                    case DOWN:
                        nextDirection = Direction.LEFT;
                }

            }
            else if (KeyboardHandler.isKeyPressed(KeyboardHandler.KEY_MOVE_UP)) {
                switch (currentDirection) {
                    case LEFT:
                    case RIGHT:
                        nextDirection = Direction.UP;
                }
            }
            else if (KeyboardHandler.isKeyPressed(KeyboardHandler.KEY_MOVE_DOWN)) {
                switch (currentDirection) {
                    case LEFT:
                    case RIGHT:
                        nextDirection = Direction.DOWN;
                }

            }
        } else {
            if (Math.random() < 0.05) {
                nextDirection = randomDirection();
            }
        }

        update++;
        if (update >= speed) {
            update = 0;

            currentDirection = nextDirection;

            // calculate next position
            int nextPos = snakeHead + currentDirection.change;

            if (currentDirection == Direction.RIGHT && nextPos % World.WIDTH == 0) nextPos -= World.WIDTH;
            else if (currentDirection == Direction.LEFT && nextPos % World.WIDTH == World.WIDTH - 1 || nextPos % World.WIDTH == -1) nextPos += World.WIDTH;
            else if (currentDirection == Direction.UP && nextPos < 0) nextPos += World.WIDTH * World.HEIGHT;
            else if (currentDirection == Direction.DOWN && nextPos > World.WIDTH * World.HEIGHT) nextPos -= World.WIDTH * World.HEIGHT;

            snakeBody.addFirst(snakeHead);
            snakeHead = nextPos;

            if (world.getPoint() == snakeHead) {
                world.resetPoint();
            } else {
                snakeBody.removeLast();
            }

            // check if dead
            snakeBody.forEach(i -> {
                    if (snakeHead == i) {
                        ignoreInput = true;
                        dead = true;
                    }
                }
            );
        }

        speed = 8 - snakeBody.size() / 8;
        if (speed < 0) speed = 0;
    }

    public void draw(Graphics2D g) {

        // Draw snake
        BufferedImage snakeHeadImage;
        switch (currentDirection) {
            case RIGHT: snakeHeadImage = Images.snake_right_mouth; break;
            case LEFT: snakeHeadImage = Images.snake_left_mouth; break;
            case UP: snakeHeadImage = Images.snake_up_mouth; break;
            default: snakeHeadImage = Images.snake_down_mouth; break;
        }

        g.drawImage(snakeHeadImage, world.getXof(snakeHead), world.getYof(snakeHead), null);

        snakeBody.forEach(i ->
                g.drawImage(Images.snake_snake_image, world.getXof(i), world.getYof(i), null)
        );
    }

    public void setIgnoreInput(boolean ignoreInput) {
        this.ignoreInput = ignoreInput;
    }

    public boolean isDead() {
        return dead;
    }

    private Direction randomDirection() {
        int i = (int) (Math.random() * 4);

        if (currentDirection == Direction.RIGHT && i == 1) {
            return currentDirection;
        }
        else if (currentDirection == Direction.LEFT && i == 0) {
            return currentDirection;
        }
        else if (currentDirection == Direction.UP && i == 3) {
            return currentDirection;
        }
        else if (currentDirection == Direction.DOWN && i == 1) {
            return currentDirection;
        }

        if (i == 0) return Direction.RIGHT;
        else if (i == 1) return Direction.LEFT;
        else if (i == 2) return Direction.UP;
        else return Direction.DOWN;



    }


}
