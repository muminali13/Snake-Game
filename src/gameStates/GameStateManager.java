package gameStates;

import main.GamePanel;
import handlers.Fonts;

import java.awt.*;

public class GameStateManager {

    private State currentState;

    public GameStateManager() {
        currentState = new IntroState(this);
    }


    public void setState(State newState) {
        currentState = newState;
    }

    public void update() {

        if (currentState != null) {
            currentState.update();
        }
    }

    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(3));

        if (currentState != null) currentState.draw(g);
        else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
            g.setColor(Color.WHITE);
            g.setFont(Fonts.font_title);
            g.drawString("ERROR", 50, 300);
        }

    }

}
