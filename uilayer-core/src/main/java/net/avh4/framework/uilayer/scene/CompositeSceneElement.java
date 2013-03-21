package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.Rect;

public abstract class CompositeSceneElement extends SceneElementBase implements Element {

    public CompositeSceneElement(final String name, final int x, final int y,
                                 final int width, final int height) {
        super(name, x, y, width, height);
    }

    public abstract Iterable<SceneElement> getSceneElements();

    @Deprecated
    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.translate(x, y);
        for (final SceneElement object : getSceneElements()) {
            Rect childBounds = new Rect(object.getX(), object.getY(), object.getWidth(), object.getHeight());
            SceneRenderer.draw(object, childBounds, g, fm);
        }
        g.translate(-x, -y);
    }
}
