package net.avh4.framework.uilayer.scene;

import java.awt.Graphics;

public abstract class SwingSceneObject {

	protected final int x;
	protected final int y;
	protected final int width;
	protected final int height;

	public SwingSceneObject(final int x, final int y, final int width,
			final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void draw(Graphics g);

}