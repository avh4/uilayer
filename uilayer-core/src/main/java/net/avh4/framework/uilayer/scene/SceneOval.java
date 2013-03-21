package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.Rect;

public class SceneOval extends SceneElementBase implements Element {

    protected final int color;

    public SceneOval(final int x, final int y, final int width,
                     final int height, final int color) {
        super(null, x, y, width, height);
        this.color = color;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawOval(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight(), color);
    }
}
