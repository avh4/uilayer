package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.SceneCreator;

import javax.swing.*;
import java.awt.*;

public class SwingSceneRenderer extends JComponent {

    private static final long serialVersionUID = 1L;
    private final SwingFontMetricsService fm = new SwingFontMetricsService();
    private final SwingGraphicsOperations graphicsOperations;
    private final GraphicsOperationsRenderer renderer;

    public SwingSceneRenderer(SceneCreator creator) {
        this(new SwingGraphicsOperations(), new SceneRenderer(creator));
    }

    public SwingSceneRenderer(Scene scene) {
        this(new SwingGraphicsOperations(), new SceneRenderer(scene));
    }

    public SwingSceneRenderer(SceneElement element) {
        this(new SwingGraphicsOperations(), new SceneRenderer(element));
    }

    public SwingSceneRenderer(SwingGraphicsOperations g, GraphicsOperationsRenderer sceneRenderer) {
        this.graphicsOperations = g;
        renderer = sceneRenderer;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(renderer.getWidth(), renderer.getHeight());
    }

    @Override
    protected void paintComponent(final Graphics g) {
        synchronized (graphicsOperations) {
            synchronized (fm) {
                graphicsOperations.setGraphicsContext(g);
                fm.setGraphicsContext(g);
                renderer.render(graphicsOperations, fm);
                graphicsOperations.setGraphicsContext(null);
                fm.setGraphicsContext(null);
            }
        }
    }
}
