package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;

public class LightswitchGame implements UI {

    private boolean lightsOn;

    public static void main(final String[] args) {
        final LightswitchGame game = new LightswitchGame();
        UILayer.main(game);
    }

    @Override
    public void click(int x, int y) {
        lightsOn = !lightsOn;
    }

    @Override
    public void key(int keyCode) {
    }

    @Override
    public Scene getScene() {
        final Scene scene = new Scene("Lightswitch");
        if (lightsOn) {
            scene.add(new ScenePlaceholder("Room", 0, 0, 800, 600));
        } else {
            scene.add(new ScenePlaceholder("Blackness", 0, 0, 800, 600, Color.BLACK));
        }
        scene.add(new ScenePlaceholder("Switch", 300, 400, 10, 17, Color.WHITE));
        return scene;
    }
}
