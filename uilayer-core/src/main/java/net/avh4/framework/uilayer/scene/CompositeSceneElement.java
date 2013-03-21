package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.Rect;

public abstract class CompositeSceneElement extends SceneElementBase implements Element {

    public CompositeSceneElement(final String name, final int x, final int y,
                                 final int width, final int height) {
        super(name, x, y, width, height);
    }

    public abstract Iterable<Item> getSceneElements(Rect bounds);

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        g.translate(x, y);
        for (final Item item : getSceneElements(bounds.topLeft(0, 0))) {
            item.element.draw(item.bounds, g, fm);
        }
        g.translate(-x, -y);
    }
}
