package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.geometry.Rect;

public class SceneCenteredText implements Element {
    protected final Font font;
    protected final int color;
    protected String text;

    public SceneCenteredText(String text, Font font, int color) {
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

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        final float ascent = fm.getAscent(font);
        final double x = bounds.minX() + (bounds.width() - fm.stringWidth(font, text)) / 2;
        final double y = bounds.minY() + ascent + (bounds.height() - ascent - fm.getDescent(font)) / 2;

        g.drawText(text, x, y, font, color);
    }
}
