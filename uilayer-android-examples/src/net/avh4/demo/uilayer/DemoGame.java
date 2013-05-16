package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.android.KeyEvent;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneImage;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.geometry.Rect;

public class DemoGame implements UI {

    public static void main(final String[] args) {
        final DemoGame game = new DemoGame();
        UILayer.main(game);
    }

    private Scene s;

    public DemoGame() {
        s = new Scene("UILayer Demo Game");
        s.add(new SceneImage("background.jpg"));
    }

    @Override
    public Scene getScene() {
        return s;
    }

    @Override
    public void click(Rect bounds, final double x, final double y) {
        s.add(new ScenePlaceholder("Box"));
    }

    @Override
    public void key(final int keyCode, boolean shift) {
        if (keyCode == KeyEvent.VK_SPACE) {
            s = new Scene("UILayer Demo Game");
            s.add(new SceneImage("background.jpg"));
        }
    }
}
