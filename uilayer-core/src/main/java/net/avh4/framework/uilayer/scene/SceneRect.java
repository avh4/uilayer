package net.avh4.framework.uilayer.scene;

public class SceneRect extends SceneElement {

    protected final int color;

    public SceneRect(final int x, final int y, final int width,
                     final int height, final int color) {
        super(null, x, y, width, height);
        this.color = color;
    }

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(x, y, width, height, color);
    }
}
