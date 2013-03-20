package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.SceneCreator;

public class SceneRenderer implements GraphicsOperationsRenderer {

    private final SceneCreator creator;

    public SceneRenderer(final SceneCreator creator) {
        this.creator = creator;
    }

    public SceneRenderer(final Scene scene) {
        this(new SceneCreator() {
            @Override
            public Scene getScene() {
                return scene;
            }
        });
    }

    public SceneRenderer(final SceneElement e) {
        this(new Scene(e));
    }

    public SceneRenderer(Image image) {
        this(new SceneImage(0, 0, image));
    }

    @Override
    public void render(int width, int height, GraphicsOperations g, FontMetricsService fm) {
        final Scene s = creator.getScene();
        if (s == null) {
            g.drawRect(0, 0, width, height, Color.GRAY);
            g.drawText("(No scene)", 0, 20, Font.PFENNIG, Color.BLACK);
            return;
        }

        g.drawRect(0, 0, width, height, Color.BLACK);

        for (final SceneElement object : s) {
            draw(object, g, fm);
        }
    }

    static void draw(final SceneElement e, GraphicsOperations g, FontMetricsService fm) {
        if (e instanceof SceneElementBase && ((SceneElementBase) e).isHidden()) {
            return;
        } else {
            e.draw(g, fm);
        }
    }
}
