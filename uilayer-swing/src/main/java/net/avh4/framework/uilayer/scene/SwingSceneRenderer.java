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

	public SwingSceneRenderer(final SceneElement e) {
		final Scene scene = new Scene(e.getName());
		scene.setSize(e.width, e.height);
		scene.add(e);
		creator = new SceneCreator() {
			@Override
			public Scene getScene() {
				return scene;
			}
		};
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
		if (e instanceof CompositeSceneElement) {
			drawComposite(g, (CompositeSceneElement) e);
		} else if (e instanceof SceneText) {
			drawText(g, (SceneText) e);
		} else if (e instanceof ScenePlaceholder) {
			drawPlaceholder(g, (ScenePlaceholder) e);
		} else if (e instanceof SceneImage) {
			drawImage(g, (SceneImage) e);
		} else if (e instanceof SceneRect) {
			drawRect(g, (SceneRect) e);
		} else if (e instanceof SceneOval) {
			drawOval(g, (SceneOval) e);
		} else if (e instanceof SceneLabel) {
			drawLabel(g, (SceneLabel) e);
		} else if (e instanceof SceneLine) {
			drawLine(g, (SceneLine) e);
		} else {
			throw new RuntimeException("Don't know how to render "
					+ e.getClass().getSimpleName());
		}
	}

	private void drawComposite(final Graphics g, final CompositeSceneElement e) {
		g.translate(e.x, e.y);
		for (final SceneElement object : e.getSceneElements()) {
			draw(g, object);
		}
		g.translate(-e.x, -e.y);
	}

	private void drawLine(final Graphics g, final SceneLine e) {
		g.setColor(loadColor(e.color));
		g.drawLine(e.x1, e.y1, e.x2, e.y2);
	}

	private void drawRect(final Graphics g, final SceneRect e) {
		g.setColor(loadColor(e.color));
		g.fillRect(e.x, e.y, e.width, e.height);
	}

	private void drawOval(final Graphics g, final SceneOval e) {
		g.setColor(loadColor(e.color));
		g.fillOval(e.x, e.y, e.width, e.height);
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

	private void drawLabel(final Graphics g, final SceneLabel e) {
		g.setColor(loadColor(e.color));
		g.setFont(SwingUILayerService.loadFont(e.font, e.fontSize));

		final FontMetrics fm = g.getFontMetrics();
		final int labelWidth = fm.stringWidth(e.text);

		final int x = e.x - labelWidth / 2;
		final int y = e.y + fm.getHeight();

		g.drawString(e.text, x, y);
	}

	private static Color loadColor(final int color) {
		return new Color(color);
	}
}
