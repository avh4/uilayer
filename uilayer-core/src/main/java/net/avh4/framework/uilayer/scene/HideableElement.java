package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.Rect;

public abstract class HideableElement extends SceneElementBase implements Element {
    protected boolean hidden;

    public HideableElement(String name, double x, double y, double width, double height) {
        super(name, x, y, width, height);
    }

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
    }

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
