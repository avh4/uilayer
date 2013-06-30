package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;

public class FontMetricsDemo implements UI {
    public static void main(String[] args) {
        UILayer.main(new FontMetricsDemo());
    }

    @Override
    public void click(Rect bounds, Point p) {
    }

    @Override
    public void move(Rect bounds, Point p) {
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        Rect r = bounds.inset(100);
        g.drawRect(r, Color.DARK_GRAY);
        final Font font = Font.OPEN_SANS.size(300);
        final String text = "fgXQ";
        final float lineHeight = fm.getLineHeight(font);
        final float ascent = fm.getAscent(font);
        final float descent = fm.getDescent(font);
        final float leading = lineHeight - ascent - descent;
        final float ascender = fm.getAscender(font);
        final float width = fm.stringWidth(font, text);
        final Rect guide = r.inset(-10, 0, -10, 0).resizeFromCenter(r.inset(-10, 0, -10, 0).width(), lineHeight);
        g.drawRect(guide, Color.Solarized.base00);
        g.drawRect(guide.top(ascent), Color.Solarized.base0);
        g.drawRect(guide.top(ascent + descent).bottom(descent), Color.Solarized.base1);
        g.drawRect(guide.top(ascent - ascender), Color.Solarized.base1);
        g.drawText(text, r.minX(), r.midY() + ascender / 2, font, Color.Solarized.magenta);
        g.drawText(fm, text, r, font, Color.Solarized.violet);
    }

    @Override
    public void key(int keyCode, boolean shift) {
    }

    @Override
    public UpdateAction time() {
        return UpdateAction.NO_UPDATE;
    }
}
