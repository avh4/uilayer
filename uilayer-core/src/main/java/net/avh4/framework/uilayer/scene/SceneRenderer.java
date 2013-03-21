package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.math.Rect;

@Deprecated
public class SceneRenderer implements Element {

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

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        final Scene s = creator.getScene();
        if (s == null) {
            g.drawRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight(), Color.GRAY);
            g.drawText("(No scene)", 0, 20, Font.PFENNIG, Color.BLACK);
            return;
        }

        g.drawRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight(), Color.BLACK);

        for (final SceneElement object : s) {
            Rect childBounds = new Rect(object.getX(), object.getY(), object.getWidth(), object.getHeight());
            object.draw(childBounds, g, fm);
        }
    }
}
