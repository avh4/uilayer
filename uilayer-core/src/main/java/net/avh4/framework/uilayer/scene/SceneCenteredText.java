package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public class SceneCenteredText extends SceneElement {
    protected final String text;
    protected final Font font;
    protected final int color;

    public SceneCenteredText(String text, int x, int y, int width, int height, Font font, int color) {
        super(text, x, y, width, height);
        this.text = text;
        this.font = font;
        this.color = color;
    }
}
