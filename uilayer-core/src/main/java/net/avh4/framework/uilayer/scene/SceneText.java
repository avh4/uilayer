package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.geometry.Rect;

public class SceneText implements Element {

    protected String text;
    protected final Font font;
    protected final int color;

    public SceneText(String text, Font customFont, int color) {
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

        double curX = bounds.minX();
        double curY = bounds.minY() + fm.getAscent(font);

        final String[] words = text.replaceAll("\n", " ").split(" ");

        for (final String word : words) {
            // Find out the width of the word.
            final float wordWidth = fm.stringWidth(font, word);

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth > bounds.minX() + bounds.width()) {
                curY += lineHeight;
                curX = bounds.minX();
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
                "text='" + text + '\'' +
                ", font=" + font +
                ", color=" + color +
                '}';
    }
}
