package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public class SceneText extends SceneElement {

    protected String text;
    protected final Font font;
    protected final int color;

    public SceneText(final String text, final int x, final int y,
                     final int width, final Font customFont,
                     final int color) {
        this(text, text, x, y, width, customFont, color);
    }

    public SceneText(String text, int x, int y, int width, String customFontResource, int fontSize, int color) {
        this(text, x, y, width, new Font(customFontResource).size(fontSize), color);
    }

    public SceneText(String name, String text, int x, int y, int width, Font customFont, int color) {
        super(name, x, y, width, customFont.getLineHeight());
        this.text = text;
        this.color = color;
        font = customFont;
    }

    public void setText(final String string) {
        text = string;
    }

    public String getText() {
        return text;
    }
}
