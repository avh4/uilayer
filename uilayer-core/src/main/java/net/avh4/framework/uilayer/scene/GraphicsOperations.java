package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;
import net.avh4.math.geometry.Rect;

public abstract class GraphicsOperations {
    public void drawRect(Rect bounds, int argbColor) {
        drawRect(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight(), argbColor);
    }

    public abstract void drawRect(double leftX, double topY, double width, double height, int argbColor);

    public abstract void drawText(String text, double leftX, double baselineY, Font font, int argbColor);

    public abstract void translate(double deltaX, double deltaY);

    public abstract void drawLine(double startX, double startY, double stopX, double stopY, int argbColor);

    public abstract void drawOval(double leftX, double topY, double width, double height, int argbColor);

    public abstract void drawImage(String image, int destLeftX, int destTopY, int destRightX, int destBottomY,
                                   int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY);
}
