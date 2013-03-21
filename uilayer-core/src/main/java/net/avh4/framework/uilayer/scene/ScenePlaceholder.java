package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.Rect;
import net.avh4.util.Util;

public class ScenePlaceholder extends SceneElementBase implements Element {

    protected final int color;
    protected final int textColor;

    public ScenePlaceholder(final String name, final double x, final double y,
                            final double width, final double height) {
        this(name, x, y, width, height, Util.getHashColor(name));
    }

    public ScenePlaceholder(String name, double x, double y, double width, double height, int color) {
        super(name, x, y, width, height);
        this.color = color;
        textColor = Util.getContrastingColor(color);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        final ScenePlaceholder b = (ScenePlaceholder) obj;
        if (b == null) {
            return false;
        }
        return name.equals(b.name) && x == b.x && y == b.y && width == b.width
                && height == b.height;
    }

    @Deprecated
    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        final int MARGIN = 5;
        double x = bounds.getMinX();
        double y = bounds.getMinY();
        double height = bounds.getHeight();
        g.drawRect(x, y, bounds.getWidth(), height, color);
        g.drawText(name, x + MARGIN, y + height - MARGIN, Font.PFENNIG, textColor);
    }
}
