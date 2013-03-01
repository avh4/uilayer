package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.SceneElement;

public abstract class UIUtil {
    public static void clickOn(UI ui, String elementName) {
        final SceneElement element = findSceneElement(ui, elementName);
        ui.click(element.getX() + element.getWidth() / 2, element.getY() + element.getHeight() / 2);
    }

    public static void clickOnTop(UI ui, String elementName) {
        final SceneElement element = findSceneElement(ui, elementName);
        ui.click(element.getX() + element.getWidth() / 2, element.getY() + 1);
    }

    public static void clickOnBottom(UI ui, String elementName) {
        final SceneElement element = findSceneElement(ui, elementName);
        ui.click(element.getX() + element.getWidth() / 2, element.getY() + element.getHeight() - 1);
    }

    private static SceneElement findSceneElement(UI ui, String elementName) {
        SceneElement sceneElement = ui.getScene().findSceneElement(elementName);
        if (sceneElement == null) {
            throw new RuntimeException("Couldn't find SceneElement '" + elementName + "'" +
                    " in " + ui.toString());
        }
        return sceneElement;
    }
}
