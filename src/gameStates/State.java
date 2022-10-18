package gameStates;

import main.GamePanel;

import java.awt.Color;
import java.awt.Rectangle;


public abstract class State
{
	protected GameStateManager gsm;

    protected static Color bgColor = new Color(12, 0, 22);
    protected static Rectangle screenRect = new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
    protected static Rectangle topRect = new Rectangle(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT / 2);

    public State(GameStateManager gsm) {
        this.gsm = gsm;
    }


	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);

}
