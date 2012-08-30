package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.SwingSceneRenderer;
import net.avh4.util.imagecomparison.Renderer;
import net.avh4.util.imagecomparison.SwingRenderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UILayerRenderer implements Renderer {
    public static final SwingRenderer SWING_RENDERER = new SwingRenderer();

    @Override
    public BufferedImage getImage(final Object obj) {
        if (obj instanceof SceneCreator) {
            final Component comp = new SwingSceneRenderer((SceneCreator) obj);
            return SWING_RENDERER.getImage(comp);
        } else if (obj instanceof SceneElement) {
            final Component comp = new SwingSceneRenderer((SceneElement) obj);
            return SWING_RENDERER.getImage(comp);
        } else {
            return null;
        }
    }
}
