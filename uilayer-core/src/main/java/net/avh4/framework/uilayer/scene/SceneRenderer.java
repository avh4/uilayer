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
            draw(object, g, fm);
        }
    }

    static void draw(final SceneElement e, GraphicsOperations g, FontMetricsService fm) {
        if (e.isHidden()) {
            return;
        } else {
            e.draw(g, fm);
        }
    }
}
