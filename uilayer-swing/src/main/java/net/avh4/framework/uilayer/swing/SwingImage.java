package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Image;
import net.avh4.math.geometry.Rect;

import java.awt.image.BufferedImage;

public class SwingImage implements Image {
    private final BufferedImage bi;

    public SwingImage(BufferedImage bi) {
        this.bi = bi;
    }

    @Override
    public int getWidth() {
        return bi.getWidth();
    }

    @Override
    public int getHeight() {
        return bi.getHeight();
    }

    @Override
    public Rect getSize() {
        return Rect.ofSize(getWidth(), getHeight());
    }

    @Override
    public int getPixel(int x, int y) {
        return bi.getRGB(x, y);
    }

    public java.awt.Image getAwtImage() {
        return bi;
    }
}
