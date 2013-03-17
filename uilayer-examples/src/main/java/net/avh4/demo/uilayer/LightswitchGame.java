package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.mvc.ViewController;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;

public class LightswitchGame implements ViewController<LightswitchGame.Model> {

    public static class Model {
        private boolean lightsOn;
    }

    public static void main(final String[] args) {
        LightswitchGame vc = new LightswitchGame();
        UILayer.main(new Model(), vc);
    }

    @Override
    public void click(Model model, int x, int y) {
        model.lightsOn = !model.lightsOn;
    }

    @Override
    public void key(Model model, int keyCode, boolean shift) {
    }

    @Override
    public Scene getScene(Model model) {
        final Scene scene = new Scene("Lightswitch");
        if (model.lightsOn) {
            scene.add(new ScenePlaceholder("Room", 0, 0, 800, 600));
        } else {
            scene.add(new ScenePlaceholder("Blackness", 0, 0, 800, 600, Color.BLACK));
        }
        scene.add(new ScenePlaceholder("Switch", 300, 400, 10, 17, Color.WHITE));
        return scene;
    }
}
