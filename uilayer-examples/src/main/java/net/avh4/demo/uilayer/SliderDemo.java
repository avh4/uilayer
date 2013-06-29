package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;

public class SliderDemo implements UI {

    public static final int SLIDER_HEIGHT = 50;
    private double hue = 0;
    private double saturation = 0.5;
    private double lightness = 0.5;
    private final Atom<Double> hueAtom = new Atom<Double>() {
        @Override
        public void swap(Double newValue) {
            hue = newValue * 360;
        }
    };
    private final Atom<Double> saturationAtom = new Atom<Double>() {
        @Override
        public void swap(Double newValue) {
            saturation = newValue;
        }
    };
    private final Atom<Double> lightnessAtom = new Atom<Double>() {
        @Override
        public void swap(Double newValue) {
            lightness = newValue;
        }
    };

    public static void main(String[] args) {
        UILayer.main(new SliderDemo());
    }

    public interface Atom<T> {
        public void swap(T newValue);
    }

    @Override
    public void click(Rect bounds, Point point) {
        final Rect sliderBounds = bounds.resizeFromCenter(300, SLIDER_HEIGHT * 3);
        clickSlider(sliderBounds.top(SLIDER_HEIGHT), point, hueAtom);
        clickSlider(sliderBounds.top(SLIDER_HEIGHT).translate(0, SLIDER_HEIGHT), point, saturationAtom);
        clickSlider(sliderBounds.top(SLIDER_HEIGHT).translate(0, 2 * SLIDER_HEIGHT), point, lightnessAtom);
    }

    @Override
    public void move(Rect bounds, Point point) {
        final Rect sliderBounds = bounds.resizeFromCenter(300, SLIDER_HEIGHT * 3);
        clickSlider(sliderBounds.top(SLIDER_HEIGHT), point, hueAtom);
        clickSlider(sliderBounds.top(SLIDER_HEIGHT).translate(0, SLIDER_HEIGHT), point, saturationAtom);
        clickSlider(sliderBounds.top(SLIDER_HEIGHT).translate(0, 2 * SLIDER_HEIGHT), point, lightnessAtom);
    }

    private void clickSlider(Rect bounds, Point point, Atom<Double> state) {
        Rect barBounds = bounds.inset(20).inset(0, 0, 50, 0);
        if (bounds.contains(point)) {
            final double v = barBounds.toPercentX(point.x());
            state.swap(Math.max(0, Math.min(1, v)));
        }
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(bounds.inset(10), Color.fromHSL(hue, saturation, lightness));
        final Rect sliderBounds = bounds.resizeFromCenter(300, SLIDER_HEIGHT * 3);
        drawSlider(g, sliderBounds.top(SLIDER_HEIGHT), hue / 360);
        drawSlider(g, sliderBounds.top(SLIDER_HEIGHT).translate(0, SLIDER_HEIGHT), saturation);
        drawSlider(g, sliderBounds.top(SLIDER_HEIGHT).translate(0, 2 * SLIDER_HEIGHT), lightness);
    }

    private void drawSlider(GraphicsOperations g, Rect bounds, double percent) {
        Rect barFrame = bounds.inset(20).inset(0, 0, 50, 0);
        Rect textBounds = bounds.right(50);
        g.drawRect(bounds.inset(5), Color.fromHSL(0, 0, 1));
        final Rect barRect = barFrame.resizeFromCenter(barFrame.width(), 1);
        g.drawRect(barRect, Color.BLACK);
        double x = barFrame.percentX(percent);
        Rect handleBounds = Rect.fromCenter(x, barFrame.midY(), 20, 20);
        g.drawOval(handleBounds, Color.DARK_GRAY);
        final String text = String.format("%.2f", percent);
        g.drawText(text, textBounds.minX(), textBounds.midY(), Font.OPEN_SANS, Color.BLACK);
    }

    @Override
    public void key(int keyCode, boolean shift) {
    }

    @Override
    public UpdateAction time() {
        return UpdateAction.NO_UPDATE;
    }
}
