package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Image;
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
        if (obj instanceof Element) {
            return new SwingSceneRenderer((Element) obj);
        } else if (obj instanceof Image) {
            return new SwingSceneRenderer((Image) obj);
        } else {
            return null;
        }
    }
}
