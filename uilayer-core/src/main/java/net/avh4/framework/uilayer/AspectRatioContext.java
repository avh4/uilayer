package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public class AspectRatioContext {

    private final GraphicsOperations g;
    private final FontMetricsService fm;
    private final Rect referenceBounds;
    private final Rect useableBounds;

    public AspectRatioContext(double width, double height, Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        this.g = g;
        this.fm = fm;
        useableBounds = bounds.aspectRatio(width, height);
        referenceBounds = Rect.ofSize(width, height);
    }

    public void draw(Rect rect, Element element) {
        element.draw(rect.scale(referenceBounds, useableBounds), g, fm);
    }
}
