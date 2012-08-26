package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;
import net.avh4.util.Util;

public class ScenePlaceholder extends SceneElement {

    protected final int color;
    protected final int textColor;

    public ScenePlaceholder(final String name, final int x, final int y,
                            final int width, final int height) {
        super(name, x, y, width, height);
        color = Util.getHashColor(name);
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

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
        final int MARGIN = 5;
        g.drawRect(x, y, width, height, color);
        g.drawText(name, x + MARGIN, y + height - MARGIN, Font.PFENNIG, textColor);
    }
}
