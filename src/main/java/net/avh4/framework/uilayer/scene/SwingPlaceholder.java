package net.avh4.framework.uilayer.scene;

import java.awt.Color;
import java.awt.Graphics;

import net.avh4.util.Util;

public class SwingPlaceholder extends SwingSceneObject {

	private static final int MARGIN = 5;
	private final String name;
	private final Color color;
	private final Color textColor;

	public SwingPlaceholder(final String name, final int x, final int y,
			final int width, final int height) {
		super(x, y, width, height);
		this.name = name;
		color = Util.getHashColor(name);
		textColor = Util.getContrastingColor(color);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		final SwingPlaceholder b = (SwingPlaceholder) obj;
		if (b == null) {
			return false;
		}
		return name.equals(b.name) && x == b.x && y == b.y && width == b.width
				&& height == b.height;
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setColor(textColor);
		g.drawString(name, x + MARGIN, y + height - MARGIN);
	}
}
