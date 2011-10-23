package net.avh4.framework.uilayer.scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.swing.SwingUILayerService;

public class SwingSceneRenderer extends JComponent {

	private static final long serialVersionUID = 1L;
	private final SceneCreator creator;

	public SwingSceneRenderer(final SceneCreator creator) {
		this.creator = creator;
	}

	@Override
	public Dimension getPreferredSize() {
		final Scene scene = creator.getScene();
		if (scene == null) {
			return new Dimension(100, 100);
		} else {
			return new Dimension(scene.getWidth(), scene.getHeight());
		}
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Scene s = creator.getScene();
		if (s == null) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawString("(No scene)", 0, 20);
			return;
		}
		final int height = s.getHeight();
		final int width = s.getWidth();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		for (final SceneElement object : s) {
			draw(g, object);
		}
	}

	private void draw(final Graphics g, final SceneElement e) {
		if (e instanceof SceneText) {
			drawText(g, (SceneText) e);
		} else if (e instanceof ScenePlaceholder) {
			drawPlaceholder(g, (ScenePlaceholder) e);
		} else if (e instanceof SceneImage) {
			drawImage(g, (SceneImage) e);
		} else if (e instanceof SceneRect) {
			drawRect(g, (SceneRect) e);
		} else {
			throw new RuntimeException("Don't know how to render "
					+ e.getClass().getSimpleName());
		}
	}

	private void drawRect(final Graphics g, final SceneRect e) {
		g.setColor(loadColor(e.color));
		g.fillRect(e.x, e.y, e.width, e.height);
	}

	private void drawImage(final Graphics g, final SceneImage e) {
		final Image image = SwingUILayerService.loadImage(e.image);
		g.drawImage(image, e.x, e.y, e.x + e.width, e.y + e.height, e.clipX,
				e.clipY, e.clipX + e.clipWidth, e.clipY + e.clipHeight, null);
	}

	private void drawPlaceholder(final Graphics g, final ScenePlaceholder e) {
		final int MARGIN = 5;
		final Font FONT = new Font(null, Font.PLAIN, 12);
		g.setColor(loadColor(e.color));
		g.fillRect(e.x, e.y, e.width, e.height);
		g.setFont(FONT);
		g.setColor(loadColor(e.textColor));
		g.drawString(e.name, e.x + MARGIN, e.y + e.height - MARGIN);
	}

	private void drawText(final Graphics g, final SceneText e) {
		g.setColor(loadColor(e.color));
		g.setFont(SwingUILayerService.loadFont(e.font, e.fontSize));

		// From
		// http://stackoverflow.com/questions/400566/full-justification-with-a-java-graphics-drawstring-replacement
		final FontMetrics fm = g.getFontMetrics();

		final int lineHeight = fm.getHeight();

		int curX = e.x;
		int curY = e.y + lineHeight;

		final String[] words = e.text.split(" ");

		for (final String word : words) {
			// Find out the width of the word.
			final int wordWidth = fm.stringWidth(word + " ");

			// If text exceeds the width, then move to next line.
			if (curX + wordWidth >= e.x + e.width) {
				curY += lineHeight;
				curX = e.x;
			}

			g.drawString(word, curX, curY);

			// Move over to the right for next word.
			curX += wordWidth;
		}
	}

	private static Color loadColor(final int color) {
		return new Color(color);
	}
}
