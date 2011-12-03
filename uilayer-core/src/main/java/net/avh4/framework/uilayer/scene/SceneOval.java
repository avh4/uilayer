package net.avh4.framework.uilayer.scene;

public class SceneOval extends SceneElement {

	protected final int color;

	public SceneOval(final int x, final int y, final int width,
			final int height, final int color) {
		super(null, x, y, width, height);
		this.color = color;
	}

}
