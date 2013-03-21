package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;

@Deprecated
public interface SceneElement extends Element {
    @Deprecated
    void draw(GraphicsOperations g, FontMetricsService fm);

    String getName();

    double getWidth();

    double getX();

    double getHeight();

    double getY();
}
