package net.avh4.framework.uilayer;

import net.avh4.math.geometry.Rect;

public interface Image {
    int getWidth();

    int getHeight();

    Rect getSize();

    int getPixel(int x, int y);
}
