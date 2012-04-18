package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.swing.SwingUILayerService;

import java.awt.*;

public class SwingFontMetricsService implements FontMetricsService {
    private Graphics g;

    @Override
    public int getAscent(Font font) {
        return getFontMetrics(font).getAscent();
    }

    @Override
    public int stringWidth(Font font, String text) {
        return getFontMetrics(font).stringWidth(text);
    }

    @Override
    public int getDescent(Font font) {
        return getFontMetrics(font).getDescent();
    }

    @Override
    public int getLineHeight(Font font) {
        return getFontMetrics(font).getHeight();
    }

    private FontMetrics getFontMetrics(Font font) {
        return g.getFontMetrics(SwingUILayerService.loadFont(font));
    }

    public void setGraphicsContext(Graphics g) {
        this.g = g;
    }
}
