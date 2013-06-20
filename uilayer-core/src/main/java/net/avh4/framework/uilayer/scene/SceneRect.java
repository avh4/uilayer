package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.geometry.Rect;

public class SceneRect implements Element {

    protected final int color;

    public SceneRect(final int color) {
        this.color = color;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(bounds, color);
    }
}
