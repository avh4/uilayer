package net.avh4.framework.uilayer.scene;

public class SceneLabel extends SceneElement {

	protected String text;
	protected final String font;
	protected final int fontSize;
	protected final int color;

	public SceneLabel(final String text, final int x, final int y,
			final String customFontResource, final int fontSize, final int color) {
		super(text, x, y, 1, 1);
		this.text = text;
		this.color = color;
		font = customFontResource;
		this.fontSize = fontSize;
	}

}
