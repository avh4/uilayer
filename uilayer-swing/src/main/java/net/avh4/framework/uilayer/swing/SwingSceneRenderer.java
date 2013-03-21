package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.SceneImage;
import net.avh4.framework.uilayer.scene.SceneRenderer;
import net.avh4.math.Rect;

import javax.swing.*;
import java.awt.*;

public class SwingSceneRenderer extends JComponent {

    private static final long serialVersionUID = 1L;
    private final SwingFontMetricsService fm = new SwingFontMetricsService();
    private final SwingGraphicsOperations graphicsOperations;
    private final Element renderer;

    public SwingSceneRenderer(SwingGraphicsOperations g, Element sceneRenderer) {
        this.graphicsOperations = g;
        renderer = sceneRenderer;
    }

    public SwingSceneRenderer(Element sceneRenderer) {
        this(new SwingGraphicsOperations(), sceneRenderer);
    }

    public SwingSceneRenderer(SceneCreator creator) {
        this(new SceneRenderer(creator));
    }

    public SwingSceneRenderer(Scene scene) {
        this(new SceneRenderer(scene));
    }

    public SwingSceneRenderer(SceneElement element) {
        this(new SceneRenderer(element));
    }

    public SwingSceneRenderer(Image image) {
        this(new SceneImage(image));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(final Graphics g) {
        synchronized (graphicsOperations) {
            synchronized (fm) {
                graphicsOperations.setGraphicsContext(g);
                fm.setGraphicsContext(g);
                Rect bounds = new Rect(0, 0, getWidth(), getHeight());
                renderer.draw(bounds, graphicsOperations, fm);
                graphicsOperations.setGraphicsContext(null);
                fm.setGraphicsContext(null);
            }
        }
    }
}
