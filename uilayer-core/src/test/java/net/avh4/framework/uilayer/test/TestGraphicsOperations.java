package net.avh4.framework.uilayer.test;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.GraphicsOperations;

public class TestGraphicsOperations implements GraphicsOperations {
    private StringBuffer sb = new StringBuffer();

    @Override
    public void drawRect(int leftX, int topY, int width, int height, int argbColor) {
        sb.append("Rectangle: ");
        sb.append(leftX).append(", ");
        sb.append(topY).append(", ");
        sb.append(width).append(", ");
        sb.append(height).append(", ");
        sb.append("0x").append(Integer.toHexString(argbColor)).append("\n");
    }

    @Override
    public void drawText(String text, float leftX, float baselineY, Font font, int argbColor) {
    }

    @Override
    public void translate(int deltaX, int deltaY) {
    }

    @Override
    public void drawLine(int startX, int startY, int stopX, int stopY, int argbColor) {
        sb.append("Line: ");
        sb.append(startX).append(", ");
        sb.append(startY).append(", ");
        sb.append(stopX).append(", ");
        sb.append(stopY).append(", ");
        sb.append("0x").append(Integer.toHexString(argbColor)).append("\n");
    }

    @Override
    public void drawOval(int leftX, int topY, int width, int height, int argbColor) {
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
}
