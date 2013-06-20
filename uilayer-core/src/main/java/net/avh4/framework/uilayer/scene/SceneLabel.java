package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.geometry.Rect;

public class SceneLabel implements Element {

    protected String text;
    protected final Font font;
    protected final int color;

    public SceneLabel(final String text, final Font font, final int color) {
        this.text = text;
        this.color = color;
        this.font = font;
    }

    public SceneLabel(final String text, final String customFontResource, final int fontSize, final int color) {
        this(text, new Font(customFontResource).size(fontSize), color);
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        final double labelWidth = fm.stringWidth(font, text);

        final double x = bounds.minX() - labelWidth / 2;
        final double y = bounds.minY() + fm.getLineHeight(font);

        g.drawText(text, x, y, font, color);
    }
}
