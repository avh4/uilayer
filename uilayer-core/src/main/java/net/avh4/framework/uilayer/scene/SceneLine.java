package net.avh4.framework.uilayer.scene;

public class SceneLine extends SceneElement {

	protected final int color;
	protected final int x1;
	protected final int y1;
	protected final int x2;
	protected final int y2;

	public SceneLine(final int color, final int x1, final int y1, final int x2,
			final int y2) {
		super(null, Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math
				.abs(y1 - y2));
		this.color = color;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

}
