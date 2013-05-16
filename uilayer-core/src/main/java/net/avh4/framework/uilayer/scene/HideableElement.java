package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.geometry.Rect;

public abstract class HideableElement implements Element {
    protected boolean hidden;

    @Override
    public final void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        if (!hidden) {
            drawGivenVisible(bounds, g, fm);
        }
    }

    protected abstract void drawGivenVisible(Rect bounds, GraphicsOperations g, FontMetricsService fm);

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }
}
