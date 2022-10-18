package UI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import input.MouseManager;
import handlers.Fonts;

public class TextButton {

    private String name;

    private Rectangle buttonRect;

    private Color transparentGray;
    private Color transparentWhite;

    private boolean mouseOver = false;
    private boolean mouseDown = false;
    private boolean buttonDown = false;
    private boolean active = true;

    public interface ButtonAction {
        void onClick();
    }

    private ButtonAction action;

    public TextButton(int x, int y, int width, int height, String name, ButtonAction action) {

        this.name = name;
        this.action = action;

        transparentGray = new Color(0, 0, 0, 55);
        transparentWhite = new Color(255, 255, 255, 55);

        buttonRect = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics2D g) {
        if (active) {
            if (mouseOver)
                g.setColor(transparentWhite);
            else
                g.setColor(transparentGray);
            g.fillRoundRect(buttonRect.x, buttonRect.y, buttonRect.width, buttonRect.height, 50, 50);

            g.setColor(Color.WHITE);
            g.setStroke(new BasicStroke(5));
            g.drawRoundRect(buttonRect.x, buttonRect.y, buttonRect.width, buttonRect.height, 50, 50);

            g.setColor(Color.WHITE);
        } else {
            g.setColor(transparentGray);
            g.drawRoundRect(buttonRect.x, buttonRect.y, buttonRect.width, buttonRect.height, 50, 50);
            g.setColor(Color.GRAY);
        }

        Fonts.drawCenteredString(g, name, buttonRect, Fonts.font_BatmanAlternate);

    }

    public void update() {
        if (active) {

            if (!MouseManager.leftMouseButton) {
                if (buttonDown) {
                    action.onClick();
                }
            }

            mouseOver = buttonRect.contains(MouseManager.position.getX(), MouseManager.position.getY());
            mouseDown = MouseManager.leftMouseButton;

            buttonDown = mouseOver && mouseDown;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

}
