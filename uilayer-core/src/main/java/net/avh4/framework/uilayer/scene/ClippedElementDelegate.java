package net.avh4.framework.uilayer.scene;

public interface ClippedElementDelegate {
    void draw(GraphicsOperations g, FontMetricsService fm,
              int clipLeftX, int clipTopY, double clipWidth, double clipHeight);
}
