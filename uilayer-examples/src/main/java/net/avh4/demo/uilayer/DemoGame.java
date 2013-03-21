package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneImage;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.Rect;

import java.awt.event.KeyEvent;

public class DemoGame implements UI {

    public static void main(final String[] args) {
        final DemoGame game = new DemoGame();
        UILayer.main(game);
    }

    private Scene s;

    public DemoGame() {
        s = new Scene("UILayer Demo Game");
        s.add(new Rect(0, 0, 800, 600), new SceneImage("background.jpg"));
    }

    @Override
    public Scene getScene() {
        return s;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        s.draw(bounds, g, fm);
    }

    @Override
    public void click(final double x, final double y) {
        s.add(new Rect(x, y, 50, 50), new ScenePlaceholder("Box"));
    }

    @Override
    public void key(final int keyCode, boolean shift) {
        if (keyCode == KeyEvent.VK_SPACE) {
            s = new Scene("UILayer Demo Game");
            s.add(new Rect(0, 0, 800, 600), new SceneImage("background.jpg"));
        }
    }
}
