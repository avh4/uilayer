package net.avh4.framework.uilayer.scene;

public abstract class SceneElement {

	protected int x;
	protected int y;
	protected final int width;
	protected final int height;
	protected final String name;

	public SceneElement(final String name, final int x, final int y,
			final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getHeight() {
		return height;
	}

	public int getY() {
		return y;
	}

}