package net.avh4.framework.uilayer.scene;

public interface SceneElement {
    @Deprecated
    void draw(GraphicsOperations g, FontMetricsService fm);

    String getName();

    int getWidth();

    int getX();

    int getHeight();

    int getY();
}
