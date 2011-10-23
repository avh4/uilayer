package net.avh4.framework.uilayer.scene;

public class SceneText extends SceneElement {

	protected String text;
	protected final String font;
	protected final int fontSize;
	protected final int color;

	public SceneText(final String text, final int x, final int y,
			final int width, final String customFontResource,
			final int fontSize, final int color) {
		super(text, x, y, width, 200);
		this.text = text;
		this.color = color;
		font = customFontResource;
		this.fontSize = fontSize;
	}

	public void setText(final String string) {
		text = string;
	}
}
