package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public class SceneCenteredText extends SceneElementBase {
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

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
        final float ascent = fm.getAscent(font);
        final float x = this.x + (width - fm.stringWidth(font, text)) / 2;
        final float y = this.y + ascent + (height - ascent - fm.getDescent(font)) / 2;

        g.drawText(text, x, y, font, color);
    }
}
