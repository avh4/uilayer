package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.geometry.Rect;

public class ClippedElement implements Element {
    protected ClippedElementDelegate delegate;
    private int clipX;
    private int clipY;

    public ClippedElement(ClippedElementDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        g.translate(x, y);
        delegate.draw(g, fm, clipX, clipY, bounds.getWidth(), bounds.getHeight());
        g.translate(-x, -y);
    }

    public void setClipPosition(int leftX, int topY) {
        this.clipX = leftX;
        this.clipY = topY;
    }

    public void setDelegate(ClippedElementDelegate delegate) {
        this.delegate = delegate;
    }
}
