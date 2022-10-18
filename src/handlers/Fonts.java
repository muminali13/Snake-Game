package handlers;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.InputStream;

public class Fonts {

	public static Font font_BatmanAlternate = loadFont("Fonts/batmanforeveralternate.ttf", 36f);
	public static Font font_title = loadFont("Fonts/tall bolder.ttf", 128f);


	private static Font loadFont(String s, float size) {
		Font font = null;

		try {
			InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream(s);
			font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(size);
			stream.close();
		} catch (Exception e) {
			System.out.println("Error loading Fonts.");
			e.printStackTrace();
			System.exit(-1);
		}
	
		return font;
	}

	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {

		FontMetrics metrics = g.getFontMetrics(font);

		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();

		g.setFont(font);
		g.drawString(text, x, y);
	}
}
