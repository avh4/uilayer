package net.avh4.framework.uilayer.mvc;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.Rect;

public interface View<M> {
    void draw(M model, Rect bounds, GraphicsOperations g, FontMetricsService fm);
}
