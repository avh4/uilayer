package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.mvc.ViewController;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.Rect;

public class LightswitchGame implements ViewController<LightswitchGame.Model> {

    public static class Model {
        private boolean lightsOn;
    }

    public static void main(final String[] args) {
        LightswitchGame vc = new LightswitchGame();
        UILayer.main(new Model(), vc);
    }

    @Override
    public void click(Model model, double x, double y) {
        model.lightsOn = !model.lightsOn;
    }

    @Override
    public void key(Model model, int keyCode, boolean shift) {
    }

    public Scene getScene(Model model, Rect bounds) {
        final Scene scene = new Scene("Lightswitch");
        if (model.lightsOn) {
            scene.add(bounds, new ScenePlaceholder("Room"));
        } else {
            scene.add(bounds, new ScenePlaceholder("Blackness", Color.BLACK));
        }
        scene.add(Rect.fromTopLeft(300, 400, 10, 17), new ScenePlaceholder("Switch", Color.WHITE));
        return scene;
    }

    @Override
    public void draw(Model model, Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        getScene(model, bounds).draw(bounds, g, fm);
    }
}
