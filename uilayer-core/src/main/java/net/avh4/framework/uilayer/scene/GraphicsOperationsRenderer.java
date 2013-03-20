package net.avh4.framework.uilayer.scene;

public interface GraphicsOperationsRenderer {
    int getWidth();

    int getHeight();

    void render(int width, int height, GraphicsOperations g, FontMetricsService fm);
}
