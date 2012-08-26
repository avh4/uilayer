package net.avh4.framework.uilayer.scene;

public interface GraphicsOperationsRenderer {
    int getWidth();

    int getHeight();

    void render(GraphicsOperations g, FontMetricsService fm);
}
