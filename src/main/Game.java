package main;

import handlers.Images;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game {

	public static void main(String[] args) {

		JFrame window = new JFrame(GamePanel.TITLE + " - By " + GamePanel.NAME);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setContentPane(new GamePanel());
		window.setResizable(false);
		window.setIconImage(Images.snake_right_mouth);
		window.pack();
		window.setVisible(true);
		
	}
}
