package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.math.Rect;

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
    public void render(double width, double height, GraphicsOperations g, FontMetricsService fm) {
        final Scene s = creator.getScene();
        if (s == null) {
            g.drawRect(0, 0, width, height, Color.GRAY);
            g.drawText("(No scene)", 0, 20, Font.PFENNIG, Color.BLACK);
            return;
        }

        g.drawRect(0, 0, width, height, Color.BLACK);

        for (final SceneElement object : s) {
            Rect bounds = new Rect(object.getX(), object.getY(), object.getWidth(), object.getHeight());
            object.draw(bounds, g, fm);
        }
    }
}
