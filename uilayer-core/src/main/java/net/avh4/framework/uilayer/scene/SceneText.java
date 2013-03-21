package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.Rect;

public class SceneText extends SceneElementBase implements Element {

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

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        int MAX_HEIGHT = 2000;
        // From
        // http://stackoverflow.com/questions/400566/full-justification-with-a-java-graphics-drawstring-replacement
        final float lineHeight = fm.getLineHeight(font);

        double curX = bounds.getMinX();
        double curY = bounds.getMinY() + fm.getAscent(font);

        final String[] words = text.replaceAll("\n", " ").split(" ");

        for (final String word : words) {
            // Find out the width of the word.
            final float wordWidth = fm.stringWidth(font, word);

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth > bounds.getMinX() + bounds.getWidth()) {
                curY += lineHeight;
                curX = x;
                if (curY >= MAX_HEIGHT + lineHeight) {
                    return;
                }
            }

            g.drawText(word, curX, curY, font, color);

            // Move over to the right for next word.
            curX += fm.stringWidth(font, word + " ");
        }
    }

    @Override
    public String toString() {
        return "SceneText{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", text='" + text + '\'' +
                ", font=" + font +
                ", color=" + color +
                '}';
    }
}
