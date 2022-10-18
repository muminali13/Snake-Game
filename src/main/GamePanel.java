package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import gameStates.GameStateManager;
import input.KeyboardHandler;
import input.MouseManager;


public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 104886683073809462L;
	
	// Dimensions for window
    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;

    private MouseManager mouseManager;

    // Constants
    public static final String TITLE = "Snake Game";
    public static final String NAME = "Mumin A.";

    // Game thread
    private Thread thread;
    private static boolean running;
    private final int targetFPS = 60;
    private long targetTime = 1000 / targetFPS;

    private BufferedImage image;
    private Graphics2D g;

    // Game State Manager
    private GameStateManager gsm;


    public GamePanel() {
        super();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        mouseManager = new MouseManager();

    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(new KeyboardHandler());
            addMouseMotionListener(mouseManager);
            addMouseListener(mouseManager);
            thread.start();
        }
    }

    private void init() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        gsm = new GameStateManager();

    }

    @Override
    public void run() {

        running = true;
        init();

        long start, elapsed, wait;

        // Game Loop
        while (running) {

            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            if (wait < 0) wait = 5;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void update() {
        gsm.update();
        KeyboardHandler.update();
    }

    public static void stop() {
        running = false;
    }

    private void draw() {
        gsm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
        g2.dispose();
    }
}
