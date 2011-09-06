package net.avh4.framework.uilayer.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class SwingText extends SwingSceneObject {

	private final String text;
	private final Font font;

	public SwingText(final String text, final int x, final int y,
			final int width, final String customFontResource, final int fontSize) {
		super(x, y, width, 200);
		font = loadFont(customFontResource, fontSize);
		this.text = text;
	}

	private static Font loadFont(final String customFontResource,
			final int fontSize) {
		final InputStream is = ClassLoader
				.getSystemResourceAsStream(customFontResource);
		if (is == null) {
			throw new RuntimeException(String.format(
					"Custom font resource not found: %s", customFontResource));
		}
		try {
			final Font font = Font.createFont(Font.TRUETYPE_FONT, is)
					.deriveFont(Font.PLAIN, fontSize);
			GraphicsEnvironment.getLocalGraphicsEnvironment()
					.registerFont(font);
			return font;
		} catch (final FontFormatException e) {
			throw new RuntimeException(String.format(
					"Couldn't open custom font: %s", customFontResource), e);
		} catch (final IOException e) {
			throw new RuntimeException(String.format(
					"Couldn't open custom font: %s", customFontResource), e);
		}
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(font);

		// From
		// http://stackoverflow.com/questions/400566/full-justification-with-a-java-graphics-drawstring-replacement
		final FontMetrics fm = g.getFontMetrics();

		final int lineHeight = fm.getHeight();

		int curX = x;
		int curY = y + lineHeight;

		final String[] words = text.split(" ");

		for (final String word : words) {
			// Find out the width of the word.
			final int wordWidth = fm.stringWidth(word + " ");

			// If text exceeds the width, then move to next line.
			if (curX + wordWidth >= x + width) {
				curY += lineHeight;
				curX = x;
			}

			g.drawString(word, curX, curY);

			// Move over to the right for next word.
			curX += wordWidth;
		}
	}

}
