package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.scene.GraphicsOperations;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SwingGraphicsOperations extends GraphicsOperations {
    public static final String PROPERTY_KEY_USE_ANTIALIASING = "uilayer.swing.antialiasing";
    public static boolean USE_ANTIALIASING = System.getProperty(PROPERTY_KEY_USE_ANTIALIASING, "true").equals("true");

    private Graphics g;
    private Graphics2D g2;

    @Override
    public void drawRect(double leftX, double topY, double width, double height, int argbColor) {
        g2.setColor(loadColor(argbColor));
        g2.fill(new Rectangle2D.Double(leftX, topY, width, height));
    }

    @Override
    public void drawText(String text, double leftX, double baselineY, Font font, int argbColor) {
        g.setColor(loadColor(argbColor));
        g.setFont(SwingUILayerService.loadFont(font));
        g.drawString(text, (int) leftX, (int) baselineY);
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        g2.translate(deltaX, deltaY);
    }

    @Override
    public void drawLine(double startX, double startY, double stopX, double stopY, int argbColor) {
        g.setColor(loadColor(argbColor));
        g.drawLine((int) startX, (int) startY, (int) stopX, (int) stopY);
    }

    @Override
    public void drawOval(double leftX, double topY, double width, double height, int argbColor) {
        g2.setColor(loadColor(argbColor));
        g2.fill(new Ellipse2D.Double(leftX, topY, width, height));
    }

    @Override
    public void drawImage(Image image, double destLeftX, double destTopY, double destRightX, double destBottomY,
                          int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY) {
        java.awt.Image awtImage = ((SwingImage) image).getAwtImage();
        g.drawImage(awtImage, (int) destLeftX, (int) destTopY, (int) destRightX, (int) destBottomY,
                sourceLeftX, sourceTopY, sourceRightX, sourceBottomY, null);
    }

    public void setGraphicsContext(Graphics g) {
        if (g == null) {
            this.g = null;
            this.g2 = null;
            return;
        }
        this.g = g;
        if (g instanceof Graphics2D) {
            this.g2 = (Graphics2D) g;
            if (USE_ANTIALIASING) {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            }
        } else {
            new RuntimeException("Wanted a Graphics2D, expect NullPointerExceptions: " + g.toString()).printStackTrace();
        }
    }

    private static Color loadColor(final int color) {
        return new Color(color, true);
    }
}
