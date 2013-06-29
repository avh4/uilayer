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
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DemoGame implements UI {

    private final SceneImage background = new SceneImage("background.jpg");
    private final ScenePlaceholder box = new ScenePlaceholder("Box");
    private final ArrayList<Point2D> boxes = new ArrayList<Point2D>();

    public static void main(final String[] args) {
        final DemoGame game = new DemoGame();
        UILayer.main(game);
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        background.draw(bounds, g, fm);
        for (Point2D point : boxes) {
            double centerX = point.getX() * bounds.width() / 800;
            double centerY = point.getY() * bounds.height() / 600;
            box.draw(Rect.fromTopLeft(centerX - 25, centerY - 25, 50, 50), g, fm);
        }
    }

    @Override
    public void click(Rect bounds, final double x, final double y) {
        boxes.add(new Point2D.Double(x, y));
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
