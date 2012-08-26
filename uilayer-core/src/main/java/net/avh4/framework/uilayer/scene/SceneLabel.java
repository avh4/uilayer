package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public class SceneLabel extends SceneElement {

    protected String text;
    protected final Font font;
    protected final int color;

    public SceneLabel(final String text, final int x, final int y,
                      final Font font, final int color) {
        super(text, x, y, 1, 1);
        this.text = text;
        this.color = color;
        this.font = font;
    }

    public SceneLabel(final String text, final int x, final int y,
                      final String customFontResource, final int fontSize, final int color) {
        this(text, x, y, new Font(customFontResource).size(fontSize), color);
    }

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
        final float labelWidth = fm.stringWidth(font, text);

        final float x = this.x - labelWidth / 2;
        final float y = this.y + fm.getLineHeight(font);

        g.drawText(text, x, y, font, color);
    }
}
