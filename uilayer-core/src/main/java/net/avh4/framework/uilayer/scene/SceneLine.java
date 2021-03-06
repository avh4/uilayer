package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.geometry.Rect;

public class SceneLine implements Element {

    protected final int color;
    protected final int x1;
    protected final int y1;
    protected final int x2;
    protected final int y2;

    public SceneLine(final int color, final int x1, final int y1, final int x2,
                     final int y2) {
        this.color = color;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawLine(x1, y1, x2, y2, color);
    }
}
