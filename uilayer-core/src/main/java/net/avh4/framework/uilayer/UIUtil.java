package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Item;

public abstract class UIUtil {
    public static void clickOn(UI ui, Object what) {
        final Item item = findSceneElement(ui, what);
        ui.click(item.bounds.getMidX(), item.bounds.getMidY());
    }

    public static void clickOnTop(UI ui, Object what) {
        final Item item = findSceneElement(ui, what);
        ui.click(item.bounds.getMidX(), item.bounds.getMinY() + 1);
    }

    public static void clickOnBottom(UI ui, Object what) {
        final Item item = findSceneElement(ui, what);
        ui.click(item.bounds.getMidX(), item.bounds.maxY() - 1);
    }

    private static Item findSceneElement(UI ui, Object what) {
        Item sceneElement = ui.getScene().findSceneElement(what);
        if (sceneElement == null) {
            throw new RuntimeException("Couldn't find SceneElement '" + what + "'" +
                    " in " + ui.toString());
        }
        return sceneElement;
    }
}
