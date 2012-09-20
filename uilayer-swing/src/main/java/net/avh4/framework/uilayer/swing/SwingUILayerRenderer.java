package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.util.imagecomparison.Renderer;
import net.avh4.util.imagecomparison.SwingRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SwingUILayerRenderer implements Renderer {
    public static final SwingRenderer SWING_RENDERER = new SwingRenderer();

    @Override
    public BufferedImage getImage(final Object obj) {
        Component comp = createComponent(obj);
        if (comp == null) {
            return null;
        } else {
            return SWING_RENDERER.getImage(comp);
        }
    }

    private Component createComponent(Object obj) {
        if (obj instanceof SceneCreator) {
            return new SwingSceneRenderer((SceneCreator) obj);
        } else if (obj instanceof Scene) {
            return new SwingSceneRenderer((Scene) obj);
        } else if (obj instanceof SceneElement) {
            return new SwingSceneRenderer((SceneElement) obj);
        } else if (obj instanceof Image) {
            return new SwingSceneRenderer((Image) obj);
        } else {
            return null;
        }
    }
}
