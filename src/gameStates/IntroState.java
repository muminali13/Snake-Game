package gameStates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import gameStates.game.GameState;
import handlers.Images;
import input.KeyboardHandler;
import main.GamePanel;

public class IntroState extends State {

	private BufferedImage logo;

	private int alpha;
	private int ticks;
	private int imgSize;

	private final int FADE_LENGTH;
	private final int WAIT_LENGTH;

	IntroState(GameStateManager gsm) {
		super(gsm);
		init();
		FADE_LENGTH = 60;
		WAIT_LENGTH = 60;

		imgSize = (int) (GamePanel.HEIGHT * 0.5);

	}

	public void init() {

		ticks = -30;
		alpha = 0;
		logo = Images.logo;

	}

	public void update() {
		handleInput();
		ticks++;
		if (ticks > 0) {
			if (ticks < FADE_LENGTH) {
				alpha = (int) (255 - 255 * (1.0 * ticks / FADE_LENGTH));
				if (alpha < 0)
					alpha = 0;
			} else if (ticks > FADE_LENGTH + WAIT_LENGTH && ticks < FADE_LENGTH + WAIT_LENGTH + FADE_LENGTH) {
				alpha = (int) (255 * (1.0 * ticks - FADE_LENGTH - WAIT_LENGTH) / FADE_LENGTH);
				if (alpha > 255)
					alpha = 255;
			}

			if (ticks > FADE_LENGTH + WAIT_LENGTH + FADE_LENGTH) {
				gsm.setState(new GameState(gsm));
			}
		}
	}

	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		if (ticks > 0) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.drawImage(
					logo,
					GamePanel.WIDTH / 2 - imgSize / 2,
					GamePanel.HEIGHT / 2 - imgSize / 2,
					imgSize,
					imgSize,
					null);

			g.setColor(new Color(0, 0, 0, alpha));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}

	public void handleInput() {
		if (KeyboardHandler.isKeyPressed(KeyboardHandler.KEY_BOOST)) {
			gsm.setState(new GameState(gsm));
		}
	}

}