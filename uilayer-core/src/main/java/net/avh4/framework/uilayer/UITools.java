package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneElement;

public class UITools {

    public static void clickSceneElement(final UI ui, final String elementName) {
        final Scene s = ui.getScene();

        for (final SceneElement e : s) {
            if (elementName.equals(e.getName())) {
                final double clickX = e.getWidth() / 2 + e.getX();
                final double clickY = e.getHeight() / 2 + e.getY();
                ui.click(clickX, clickY);
                return;
            }
        }

        throw new RuntimeException(String.format("No element found: %s",
                elementName));
    }
}
