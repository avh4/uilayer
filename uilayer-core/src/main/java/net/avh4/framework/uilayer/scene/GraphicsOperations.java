package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public interface GraphicsOperations {
    void drawRect(int leftX, int topY, int width, int height, int argbColor);

    void drawText(String text, int leftX, int baselineY, Font font, int argbColor);

    void translate(int deltaX, int deltaY);

    void drawLine(int leftX, int topY, int rightX, int bottomY, int argbColor);

    void drawOval(int leftX, int topY, int width, int height, int argbColor);

    void drawImage(String image, int destLeftX, int destTopY, int destRightX, int destBottomY,
                   int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY);
}
