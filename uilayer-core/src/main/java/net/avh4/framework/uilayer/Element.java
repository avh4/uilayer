package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

public interface Element {
    void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm);
}
