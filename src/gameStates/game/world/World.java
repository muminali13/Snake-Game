package gameStates.game.world;

import handlers.Images;
import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;


public class World {

    public static final int GRID_SIZE = 25;
    public static final int WIDTH = (GamePanel.WIDTH / GRID_SIZE - 2);
    public static final int HEIGHT = (GamePanel.HEIGHT / GRID_SIZE - 2);

    private final int gridOffsetX = (GamePanel.WIDTH - WIDTH * GRID_SIZE) / 2;
    private final int gridOffsetY = (GamePanel.HEIGHT - HEIGHT * GRID_SIZE) / 2;

    private final Vector2D[] gridPositions;

    private Random random = new Random();
    private int point;

    public World() {

        gridPositions = new Vector2D[WIDTH * HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                gridPositions[i * WIDTH + j] = new Vector2D(gridOffsetX + j * GRID_SIZE, gridOffsetY + i * GRID_SIZE);
            }
        }

        resetPoint();
    }

    public void update() {

    }

    public void draw(Graphics2D g) {

        // Draw border for Game
        g.setColor(Color.WHITE);
        g.drawRect(gridOffsetX - 2, gridOffsetY - 2, WIDTH * GRID_SIZE + 4, HEIGHT * GRID_SIZE + 4);

        // Draw Point
        g.drawImage(Images.snake_point_image, getXof(point), getYof(point), null);
    }

    public int getXof(int index) {
        if (index < 0 || index >= gridPositions.length) return -GRID_SIZE;

        return gridPositions[index].getX();
    }

    public int getYof(int index) {
        if (index < 0 || index >= gridPositions.length) return -GRID_SIZE;

        return gridPositions[index].getY();
    }

    public int getPoint() {
        return point;
    }

    public void resetPoint() {
        point = random.nextInt(gridPositions.length);
    }

}
