package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.geometry.Rect;

public class SceneOval implements Element {

    protected final int color;

    public SceneOval(final int color) {
        this.color = color;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawOval(bounds.minX(), bounds.minY(), bounds.width(), bounds.height(), color);
    }
}
