package gameStates.game;

import UI.ButtonPanel;
import gameStates.State;
import gameStates.GameStateManager;
import gameStates.game.world.Snake;
import gameStates.game.world.World;
import handlers.Fonts;
import input.KeyboardHandler;
import main.GamePanel;

import java.awt.*;
import java.util.ArrayList;


public class GameState extends State {

    // game
    private World world;
    private Snake player;

    // menu background
    private Snake[] bgSnakes;

    // menu
    private Color backgroundDim = new Color(0, 0, 0, 100);
    private boolean menu, paused;
    private ButtonPanel startMenuButtons;
    private ButtonPanel pauseMenuButtons;

    // transition
    private int eventCount = 0;
    private boolean eventStart;
    private ArrayList<Rectangle> tb;


    public GameState(GameStateManager gsm) {
        super(gsm);

        // menu
        menu = true;

        startMenuButtons = new ButtonPanel(GamePanel.WIDTH/4, GamePanel.HEIGHT/2, GamePanel.WIDTH/2, GamePanel.HEIGHT/4, 2);
        startMenuButtons.addButton("PLAY", this::startGame);
        startMenuButtons.addButton("QUIT", () -> System.exit(0));

        pauseMenuButtons = new ButtonPanel(GamePanel.WIDTH/4, GamePanel.HEIGHT/2, GamePanel.WIDTH/2, GamePanel.HEIGHT/2 - 30, 3);
        pauseMenuButtons.addButton("RESUME", () -> menu = paused = false);
        pauseMenuButtons.addButton("RESTART", this::startGame);
        pauseMenuButtons.addButton("QUIT", () -> System.exit(0));

        world = new World();
        bgSnakes = new Snake[5];
        for (int i = 0; i < bgSnakes.length; i++) {
            bgSnakes[i] = new Snake(world);
            bgSnakes[i].setIgnoreInput(true);
        }

        // start event
        eventStart = true;
        tb = new ArrayList<>();
        start();
    }

    private void startGame() {

        player = new Snake(world);
        menu = false;
    }

    public void update() {

        if (menu) {

            // play events
            if (eventStart) start();

            if (paused) {
                pauseMenuButtons.update();
                if (KeyboardHandler.isKeyPressed(KeyboardHandler.KEY_PAUSE)) {
                    menu = paused = false;
                }

            } else {
                startMenuButtons.update();
                for (Snake bgSnake : bgSnakes) {
                    bgSnake.update();
                }
            }

        } else {
            if (player.isDead()) {

                eventCount++;
                if (eventCount == 60) menu = true;

            } else {

                if (KeyboardHandler.isKeyPressed(KeyboardHandler.KEY_PAUSE)) {
                    menu = paused = true;
                }

                world.update();
                player.update();

                if (player.isDead())
                    eventCount = 0;
            }
        }
    }

    public void draw(Graphics2D g) {

        // Fill Background
        g.setColor(bgColor);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        world.draw(g);

        if (menu) {

            if (paused) {

                player.draw(g);

                // dim the game
                g.setColor(backgroundDim);
                g.fill(screenRect);

                // Draw title
                g.setColor(Color.WHITE);
                Fonts.drawCenteredString(g, "Game Paused", topRect, Fonts.font_title);

                // draw menu options
                pauseMenuButtons.draw(g);

            } else {

                // draw background snakes
                for (Snake bgSnake : bgSnakes) {
                    bgSnake.draw(g);
                }

                // dim the background
                g.setColor(backgroundDim);
                g.fill(screenRect);

                // Draw title
                g.setColor(Color.WHITE);
                Fonts.drawCenteredString(g, GamePanel.TITLE, topRect, Fonts.font_title);

                // draw menu options
                startMenuButtons.draw(g);

            }

            // draw transition
            if (eventStart) {
                g.setColor(bgColor);
                tb.forEach(g::fill);
            }

        } else {
            player.draw(g);

            if (player.isDead()) {
                // dim the background
                g.setColor(backgroundDim);
                g.fill(screenRect);

                // draw game over text
                g.setColor(Color.RED);
                Fonts.drawCenteredString(g, "Game Over!", topRect, Fonts.font_title);
            }
        }

    }


    ////////////////////////////////////////////////////////
    ////////////////////// Transition //////////////////////

    private void start() {
        eventCount++;
        if (eventCount == 1) {
            tb.clear();
            tb.add(new Rectangle(0, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT));
            tb.add(new Rectangle(GamePanel.WIDTH/2, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT));
        } else if (eventCount < 33) {
            tb.get(0).x -= GamePanel.WIDTH/60;
            tb.get(1).x += GamePanel.WIDTH/60;
        } else {
            eventStart = false;
            eventCount = 0;
            tb.clear();
        }
    }
}
