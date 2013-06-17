package net.avh4.framework.uilayer.test;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.GraphicsOperations;

public class TestGraphicsOperations extends GraphicsOperations {
    private StringBuffer sb = new StringBuffer();
    private int translateX = 0;
    private int translateY = 0;

    @Override
    public void drawRect(double leftX, double topY, double width, double height, int argbColor) {
        sb.append("Rectangle: ");
        sb.append(leftX).append(", ");
        sb.append(topY).append(", ");
        sb.append(width).append(", ");
        sb.append(height).append(", ");
        sb.append("0x").append(Integer.toHexString(argbColor)).append("\n");
    }

    @Override
    public void drawText(String text, double leftX, double baselineY, Font font, int argbColor) {
        sb.append("Text: \"").append(text).append("\" ");
        sb.append(leftX).append(", ");
        sb.append(baselineY).append(" ");
        sb.append(font).append(" ");
        sb.append("0x").append(Integer.toHexString(argbColor)).append("\n");
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        translateX += deltaX;
        translateY += deltaY;
        sb.append("=== TRANSLATE to ");
        sb.append(translateX).append(", ");
        sb.append(translateY);
        sb.append(" ===\n");
    }

    @Override
    public void drawLine(double startX, double startY, double stopX, double stopY, int argbColor) {
        sb.append("Line: ");
        sb.append(startX).append(", ");
        sb.append(startY).append(", ");
        sb.append(stopX).append(", ");
        sb.append(stopY).append(", ");
        sb.append("0x").append(Integer.toHexString(argbColor)).append("\n");
    }

    @Override
    public void drawOval(double leftX, double topY, double width, double height, int argbColor) {
        sb.append("Oval: ");
        sb.append(leftX).append(", ");
        sb.append(topY).append(", ");
        sb.append(width).append(", ");
        sb.append(height).append(", ");
        sb.append("0x").append(Integer.toHexString(argbColor)).append("\n");
    }

    @Override
    public void drawImage(String image, int destLeftX, int destTopY, int destRightX, int destBottomY, int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY) {
        sb.append("Image: ");
        sb.append(destLeftX).append(", ");
        sb.append(destTopY).append(", ");
        sb.append(destRightX).append(", ");
        sb.append(destBottomY).append("\n");
        sb.append("    from \"").append(image).append("\": ");
        sb.append(sourceLeftX).append(", ");
        sb.append(sourceTopY).append(", ");
        sb.append(sourceRightX).append(", ");
        sb.append(sourceBottomY).append("\n");
    }

    public String getRendering() {
        return sb.toString();
    }

    public void reset() {
        sb = new StringBuffer();
    }

    public void drawMockOperation(String mockOperationDescription) {
        sb.append(mockOperationDescription).append("\n");
    }
}
