package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.ElementBoundsAnimator;
import net.avh4.framework.uilayer.TimeService;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.animation.AnimatedBoolean;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;

public class FavoriteColors implements UI {

    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 5;
    private final ColorElement[][] colors = new ColorElement[NUM_COLS][NUM_ROWS];
    private final ElementBoundsAnimator animator = new ElementBoundsAnimator(new TimeService());

    public static class ColorElement implements Element {
        private final int color;
        private final AnimatedBoolean dismissed = new AnimatedBoolean(5000);

        public ColorElement(int color) {
            this.color = color;
        }

        @Override
        public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
            Rect rect = Rect.interpolate(
                    bounds.inset(Math.sqrt(bounds.area()) / 50),
                    bounds.resizeFromCenter(5, 5),
                    dismissed.get());
            g.drawRect(rect, color);
        }

        public void dismiss() {
            dismissed.set(true);
        }
    }

    public FavoriteColors() {
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                double hue = 360. * ((7 * col + 11 * row + 3) % 12) / 12;
                double lightness = .3 + .7 * (row + .5) / NUM_ROWS;
                colors[col][row] = new ColorElement(Color.fromHSL(hue, 1, lightness));
            }
        }
    }

    public static void main(String[] args) {
        UILayer.main(new FavoriteColors());
    }

    @Override
    public void click(Rect bounds, double x, double y) {
        int col = (int) (x * NUM_COLS / bounds.width());
        int row = (int) (y * NUM_ROWS / bounds.height());

        colors[col][row].dismiss();
    }

    @Override
    public void move(Rect bounds, Point p) {
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(bounds, Color.WHITE);

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                Rect colorBounds = bounds.divide(col / (double) NUM_COLS, row / (double) NUM_ROWS, (col + 1.) / NUM_COLS, (row + 1.) / NUM_ROWS);
                animator.updateElement(colorBounds, colors[col][row]);
            }
        }

        animator.render(g, fm);
    }

    @Override
    public void key(int keyCode, boolean shift) {
    }

    @Override
    public UpdateAction time() {
        return NEEDS_REDRAW;
    }
}
