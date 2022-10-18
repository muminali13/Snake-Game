package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardHandler implements KeyListener {

    public static ArrayList<Integer> keyPressed;
    public static ArrayList<Integer> lastKeyPressed;

    public static int KEY_MOVE_UP = KeyEvent.VK_W;
    public static int KEY_MOVE_LEFT = KeyEvent.VK_A;
    public static int KEY_MOVE_RIGHT = KeyEvent.VK_D;
    public static int KEY_MOVE_DOWN = KeyEvent.VK_S;
    public static int KEY_PAUSE = KeyEvent.VK_ESCAPE;

    public KeyboardHandler() {
        super();

        keyPressed = new ArrayList<>(4);
        lastKeyPressed = new ArrayList<>(4);
    }

    public static void update() {
        lastKeyPressed.clear();
        lastKeyPressed.addAll(keyPressed);
    }

    public static boolean isKeyPressed(int key) {
        return keyPressed.contains(key) && !lastKeyPressed.contains(key);
    }

    public static boolean isKeyDown(int key) {
        return keyPressed.contains(key);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keyPressed.contains(e.getKeyCode())) keyPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (keyPressed.contains(e.getKeyCode())) keyPressed.remove(((Integer) e.getKeyCode()));
    }
}
