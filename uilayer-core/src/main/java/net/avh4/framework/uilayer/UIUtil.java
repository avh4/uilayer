package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.SceneElementBase;

public abstract class UIUtil {
    public static void clickOn(UI ui, String elementName) {
        final SceneElementBase element = ui.getScene().findSceneElement(elementName);
        ui.click(element.getX() + element.getWidth() / 2, element.getY() + element.getHeight() / 2);
    }

    public static void clickOnTop(UI ui, String elementName) {
        final SceneElementBase element = ui.getScene().findSceneElement(elementName);
        ui.click(element.getX() + element.getWidth() / 2, element.getY() + 1);
    }

    public static void clickOnBottom(UI ui, String elementName) {
        final SceneElementBase element = ui.getScene().findSceneElement(elementName);
        ui.click(element.getX() + element.getWidth() / 2, element.getY() + element.getHeight() - 1);
    }
}
