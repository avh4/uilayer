package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.geometry.Rect;

public abstract class CompositeSceneElement implements Element {

    public abstract Iterable<Item> getSceneElements(Rect bounds);

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        double x = bounds.minX();
        double y = bounds.minY();
        g.translate(x, y);
        for (final Item item : getSceneElements(bounds.topLeft(0, 0))) {
            item.element.draw(item.bounds, g, fm);
        }
        g.translate(-x, -y);
    }
}
