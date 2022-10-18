package UI;

import java.awt.Graphics2D;


public class ButtonPanel {

    private int x, y, width, height;
    private TextButton[] buttons;
    private int buttonsAdded = 0;

    public ButtonPanel(int x, int y, int width, int height, int numButtons) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        buttons = new TextButton[numButtons];
    }

    public void addButton(String buttonName, TextButton.ButtonAction a) {
        int buttonHeight = (height - buttons.length * 15)/buttons.length;
        buttons[buttonsAdded] = new TextButton(x, y + buttonsAdded * (buttonHeight + 15), width, buttonHeight, buttonName, a);
        buttonsAdded++;
    }

    public void update() {
        for (TextButton b: buttons) {
            b.update();
        }
    }

    public void draw(Graphics2D g) {
        for (TextButton b : buttons) {
            b.draw(g);
        }
    }

}
