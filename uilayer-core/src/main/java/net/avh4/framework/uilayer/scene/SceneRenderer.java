package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.SceneCreator;

public class SceneRenderer {

    private final SceneCreator creator;
    private final GraphicsOperations g;
    private final FontMetricsService fm;

    public SceneRenderer(final SceneCreator creator, GraphicsOperations g, FontMetricsService fm) {
        this.creator = creator;
        this.g = g;
        this.fm = fm;
    }

    public int getWidth() {
        final Scene scene = creator.getScene();
        if (scene == null) {
            return 100;
        } else {
            return scene.getWidth();
        }
    }

    public int getHeight() {
        final Scene scene = creator.getScene();
        if (scene == null) {
            return 100;
        } else {
            return scene.getHeight();
        }
    }

    public void render() {
        final Scene s = creator.getScene();
        if (s == null) {
            g.drawRect(0, 0, getWidth(), getHeight(), Color.GRAY);
            g.drawText("(No scene)", 0, 20, Font.PFENNIG, Color.BLACK);
            return;
        }
        final int height = s.getHeight();
        final int width = s.getWidth();

        g.drawRect(0, 0, width, height, Color.BLACK);

        for (final SceneElement object : s) {
            draw(object);
        }
    }

    private void draw(final SceneElement e) {
        if (e.isHidden()) {
            return;
        } else if (e instanceof CompositeSceneElement) {
            drawComposite((CompositeSceneElement) e);
        } else if (e instanceof SceneCenteredText) {
            drawCenteredText((SceneCenteredText) e);
        } else if (e instanceof SceneText) {
            drawText((SceneText) e);
        } else if (e instanceof ScenePlaceholder) {
            drawPlaceholder((ScenePlaceholder) e);
        } else if (e instanceof SceneImage) {
            drawImage((SceneImage) e);
        } else if (e instanceof SceneRect) {
            drawRect((SceneRect) e);
        } else if (e instanceof SceneOval) {
            drawOval((SceneOval) e);
        } else if (e instanceof SceneLabel) {
            drawLabel((SceneLabel) e);
        } else if (e instanceof SceneLine) {
            drawLine((SceneLine) e);
        } else {
            throw new RuntimeException("Don't know how to render "
                    + e.getClass().getSimpleName());
        }
    }

    private void drawComposite(final CompositeSceneElement e) {
        g.translate(e.x, e.y);
        for (final SceneElement object : e.getSceneElements()) {
            draw(object);
        }
        g.translate(-e.x, -e.y);
    }

    private void drawCenteredText(SceneCenteredText e) {
        final float ascent = fm.getAscent(e.font);
        final float x = e.x + (e.width - fm.stringWidth(e.font, e.text)) / 2;
        final float y = e.y + ascent + (e.height - ascent - fm.getDescent(e.font)) / 2;

        g.drawText(e.text, x, y, e.font, e.color);
    }

    private void drawLine(final SceneLine e) {
        g.drawLine(e.x1, e.y1, e.x2, e.y2, e.color);
    }

    private void drawRect(final SceneRect e) {
        g.drawRect(e.x, e.y, e.width, e.height, e.color);
    }

    private void drawOval(final SceneOval e) {
         g.drawOval(e.x, e.y, e.width, e.height, e.color);
    }

    private void drawImage(final SceneImage e) {
        g.drawImage(e.image, e.x, e.y, e.x + e.width, e.y + e.height, e.clipX,
                e.clipY, e.clipX + e.clipWidth, e.clipY + e.clipHeight);
    }

    private void drawPlaceholder(final ScenePlaceholder e) {
        final int MARGIN = 5;
        g.drawRect(e.x, e.y, e.width, e.height, e.color);
        g.drawText(e.name, e.x + MARGIN, e.y + e.height - MARGIN, Font.PFENNIG, e.textColor);
    }

    private void drawText(final SceneText e) {
        // From
        // http://stackoverflow.com/questions/400566/full-justification-with-a-java-graphics-drawstring-replacement
        final float lineHeight = fm.getLineHeight(e.font);

        int curX = e.x;
        float curY = e.y + fm.getAscent(e.font);

        final String[] words = e.text.replaceAll("\n", " ").split(" ");

        for (final String word : words) {
            // Find out the width of the word.
            final float wordWidth = fm.stringWidth(e.font, word);

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth >= e.x + e.width) {
                curY += lineHeight;
                curX = e.x;
                if (curY >= getHeight() + lineHeight) {
                    return;
                }
            }

            g.drawText(word, curX, curY, e.font, e.color);

            // Move over to the right for next word.
            curX += fm.stringWidth(e.font, word + " ");
        }
    }

    private void drawLabel(final SceneLabel e) {
        final float labelWidth = fm.stringWidth(e.font, e.text);

        final float x = e.x - labelWidth / 2;
        final float y = e.y + fm.getLineHeight(e.font);

        g.drawText(e.text, x, y, e.font, e.color);
    }
}
