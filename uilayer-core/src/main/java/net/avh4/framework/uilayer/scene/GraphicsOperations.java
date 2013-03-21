package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public interface GraphicsOperations {
    void drawRect(double leftX, double topY, double width, double height, int argbColor);

    void drawText(String text, double leftX, double baselineY, Font font, int argbColor);

    void translate(double deltaX, double deltaY);

    void drawLine(int startX, int startY, int stopX, int stopY, int argbColor);

    void drawOval(double leftX, double topY, double width, double height, int argbColor);

    void drawImage(String image, int destLeftX, int destTopY, int destRightX, int destBottomY,
                   int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY);
}
