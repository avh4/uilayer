package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public class SceneCenteredText extends SceneElement {
    protected final Font font;
    protected final int color;
    protected String text;

    public SceneCenteredText(String text, int x, int y, int width, int height, Font font, int color) {
        super(text, x, y, width, height);
        this.text = text;
        this.font = font;
        this.color = color;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
