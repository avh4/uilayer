package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.GraphicsOperations;

public interface Image {
    String getName();

    int getWidth();

    int getHeight();

    void drawImage(GraphicsOperations g, int clipX, int clipY, int clipWidth, int clipHeight, double width, double height);
}
