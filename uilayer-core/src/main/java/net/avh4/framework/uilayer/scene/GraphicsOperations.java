package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.Image;
import net.avh4.math.geometry.Rect;

public abstract class GraphicsOperations {
    public void drawRect(Rect bounds, int argbColor) {
        drawRect(bounds.minX(), bounds.minY(), bounds.width(), bounds.height(), argbColor);
    }

    public abstract void drawRect(double leftX, double topY, double width, double height, int argbColor);

    public abstract void drawText(String text, double leftX, double baselineY, Font font, int argbColor);

    public void drawText(FontMetricsService fm, String text, Rect bounds, Font font, int color) {
        double ascender = fm.getAscender(font);
        drawText(text, bounds.minX(), bounds.midY() + ascender / 2, font, color);
    }

    public abstract void translate(double deltaX, double deltaY);

    public abstract void drawLine(double startX, double startY, double stopX, double stopY, int argbColor);

    public void drawOval(Rect bounds, int argbColor) {
        drawOval(bounds.minX(), bounds.minY(), bounds.width(), bounds.height(), argbColor);
    }

    public abstract void drawOval(double leftX, double topY, double width, double height, int argbColor);

    public abstract void drawImage(Image image, double destLeftX, double destTopY, double destRightX, double destBottomY,
                                   int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY);

    public void drawImage(Image image, Rect bounds, Rect sourceClip) {
        drawImage(image, bounds.minX(), bounds.minY(), bounds.maxX(), bounds.maxY(),
                (int) sourceClip.minX(), (int) sourceClip.minY(), (int) sourceClip.maxX(), (int) sourceClip.maxY());
    }

    public void drawImage(Image image, Rect bounds) {
        drawImage(image, bounds.minX(), bounds.minY(), bounds.maxX(), bounds.maxY(),
                0, 0, image.getWidth(), image.getHeight());
    }

    private void drawImage(Image image, Rect bounds, int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY) {
        drawImage(image, bounds.minX(), bounds.minY(), bounds.maxX(), bounds.maxY(),
                sourceLeftX, sourceTopY, sourceRightX, sourceBottomY);
    }
}
