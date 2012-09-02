package net.avh4.framework.uilayer.scene;

public class ClippedElement extends SceneElementBase {
    protected ClippedElementDelegate delegate;
    private int clipX;
    private int clipY;

    public ClippedElement(ClippedElementDelegate delegate, int x, int y, int width, int height) {
        super(null, x, y, width, height);
        this.delegate = delegate;
    }

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
        g.translate(x, y);
        delegate.draw(g, fm, clipX, clipY, width, height);
        g.translate(-x, -y);
    }

    public void setClipPosition(int leftX, int topY) {
        this.clipX = leftX;
        this.clipY = topY;
    }

    public void setDelegate(ClippedElementDelegate delegate) {
        this.delegate = delegate;
    }
}
