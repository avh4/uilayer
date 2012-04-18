package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.swing.SwingUILayerService;

import java.awt.*;

public class SwingGraphicsOperations implements GraphicsOperations {
    private Graphics g;

    @Override
    public void drawRect(int leftX, int topY, int width, int height, int argbColor) {
        g.setColor(loadColor(argbColor));
        g.fillRect(leftX, topY, width, height);
    }

    @Override
    public void drawText(String text, float leftX, float baselineY, Font font, int argbColor) {
        g.setColor(loadColor(argbColor));
        g.setFont(SwingUILayerService.loadFont(font));
        g.drawString(text, (int) leftX, (int) baselineY);
    }

    @Override
    public void translate(int deltaX, int deltaY) {
        g.translate(deltaX, deltaY);
    }

    @Override
    public void drawLine(int startX, int startY, int stopX, int stopY, int argbColor) {
        g.setColor(loadColor(argbColor));
        g.drawLine(startX, startY, stopX, stopY);
    }

    @Override
    public void drawOval(int leftX, int topY, int width, int height, int argbColor) {
        g.setColor(loadColor(argbColor));
        g.fillOval(leftX, topY, width, height);
    }

    @Override
    public void drawImage(String image, int destLeftX, int destTopY, int destRightX, int destBottomY,
                          int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY) {
        g.drawImage(SwingUILayerService.loadImage(image), destLeftX, destTopY, destRightX, destBottomY,
                sourceLeftX, sourceTopY, sourceRightX, sourceBottomY, null);
    }

    public void setGraphicsContext(Graphics g) {
        this.g = g;
    }

    private static Color loadColor(final int color) {
        return new Color(color);
    }
}
