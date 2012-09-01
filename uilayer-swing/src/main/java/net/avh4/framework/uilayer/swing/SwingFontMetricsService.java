package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.FontMetricsService;

import java.awt.*;

public class SwingFontMetricsService implements FontMetricsService {
    private Graphics g;

    @Override
    public float getAscent(Font font) {
        return getFontMetrics(font).getAscent();
    }

    @Override
    public float stringWidth(Font font, String text) {
        return getFontMetrics(font).stringWidth(text);
    }

    @Override
    public float getDescent(Font font) {
        return getFontMetrics(font).getDescent();
    }

    @Override
    public float getLineHeight(Font font) {
        return getFontMetrics(font).getHeight();
    }

    private FontMetrics getFontMetrics(Font font) {
        return g.getFontMetrics(SwingUILayerService.loadFont(font));
    }

    public void setGraphicsContext(Graphics g) {
        this.g = g;
    }
}
