package net.avh4.framework.uilayer.scene;

public interface SceneElement {
    void draw(GraphicsOperations g, FontMetricsService fm);

    String getName();

    int getWidth();

    int getX();

    int getHeight();

    int getY();
}
