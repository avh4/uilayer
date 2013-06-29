package net.avh4.demo.uilayer;

import com.google.common.collect.ImmutableList;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.layout.BoundsTransformation;
import net.avh4.framework.uilayer.layout.Layout;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;

import java.util.Arrays;
import java.util.List;

public class LayoutDemo implements UI {

    private final Layout<String> layout = new EventVerticalLayout<String>("A", "B", "C");

    public LayoutDemo() {
    }

    public static class EventVerticalLayout<T> implements Layout<T> {
        private final List<T> items;

        public EventVerticalLayout(T... items) {
            this.items = Arrays.asList(items);
        }

        @Override
        public BoundsTransformation get(T key) {
            final int index = items.indexOf(key);
            if (index == -1) return null;
            final int n = items.size();
            return new BoundsTransformation() {
                @Override
                public Rect apply(Rect bounds) {
                    return bounds.divide(0, (double) index / n, 1, (double) (index + 1) / n);
                }
            };
        }
    }

    public static void main(String[] args) {
        UILayer.main(new LayoutDemo());
    }

    @Override
    public void click(Rect bounds, Point p) {
    }

    @Override
    public void move(Rect bounds, Point p) {
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        for (String key : ImmutableList.of("A", "B", "C")) {
            Rect b = layout.get(key).apply(bounds);
            new ScenePlaceholder(key).draw(b, g, fm);
        }
    }

    @Override
    public void key(int keyCode, boolean shift) {
    }

    @Override
    public UpdateAction time() {
        return UpdateAction.NO_UPDATE;
    }
}
