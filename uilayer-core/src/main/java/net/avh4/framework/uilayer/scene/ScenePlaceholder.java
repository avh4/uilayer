package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.geometry.Rect;
import net.avh4.util.Util;

public class ScenePlaceholder extends HideableElement implements Element {

    private final String name;
    protected final int color;
    protected final int textColor;

    public ScenePlaceholder(final String name) {
        this(name, Util.getHashColor(name));
    }

    public ScenePlaceholder(String name, int color) {
        this.name = name;
        this.color = color;
        textColor = Util.getContrastingColor(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScenePlaceholder that = (ScenePlaceholder) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public void drawGivenVisible(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        final int MARGIN = 5;
        double x = bounds.minX();
        double y = bounds.minY();
        double height = bounds.height();
        g.drawRect(x, y, bounds.width(), height, color);
        g.drawText(name, x + MARGIN, y + height - MARGIN, Font.PFENNIG, textColor);
    }
}
