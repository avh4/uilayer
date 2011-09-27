package net.avh4.framework.uilayer.swing.scene;

import java.awt.Color;
import java.awt.Graphics;

import net.avh4.framework.uilayer.scene.SceneObject;

public class SwingRect extends SceneObject<Graphics> {

	private final Color color;

	public SwingRect(final int x, final int y, final int width,
			final int height, final int color) {
		super(x, y, width, height);
		this.color = new Color(color);
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

}
