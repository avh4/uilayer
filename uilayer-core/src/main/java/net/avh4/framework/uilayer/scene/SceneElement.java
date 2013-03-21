package net.avh4.framework.uilayer.scene;

public interface SceneElement {
    @Deprecated
    void draw(GraphicsOperations g, FontMetricsService fm);

    String getName();

    double getWidth();

    double getX();

    double getHeight();

    double getY();
}
