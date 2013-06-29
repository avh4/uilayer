package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.SceneImage;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class DemoGame implements UI {

    private final SceneImage background = new SceneImage("background.jpg");
    private final ScenePlaceholder box = new ScenePlaceholder("Box");
    private final ArrayList<Point> boxes = new ArrayList<Point>();

    public static void main(final String[] args) {
        final DemoGame game = new DemoGame();
        UILayer.main(game);
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        background.draw(bounds, g, fm);
        for (Point point : boxes) {
            Point center = point.translate(Rect.unit(), bounds);
            box.draw(Rect.fromCenter(center, 50, 50), g, fm);
        }
    }

    @Override
    public void click(Rect bounds, final Point p) {
        boxes.add(p.translate(bounds, Rect.unit()));
    }

    @Override
    public void move(Rect bounds, Point p) {
    }

    @Override
    public void key(final int keyCode, boolean shift) {
        if (keyCode == KeyEvent.VK_SPACE) {
            boxes.clear();
        }
    }

    @Override
    public UpdateAction time() {
        return NO_UPDATE;
    }
}
