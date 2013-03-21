package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.Rect;

public class ElementRenderer implements GraphicsOperationsRenderer {
    private final Element element;

    public ElementRenderer(Element element) {
        this.element = element;
    }

    @Override
    public void render(double width, double height, GraphicsOperations g, FontMetricsService fm) {
        Rect bounds = new Rect(0, 0, width, height);
        element.draw(bounds, g, fm);
    }
}
