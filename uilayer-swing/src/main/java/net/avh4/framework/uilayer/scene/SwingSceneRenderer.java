package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.SceneCreator;

import javax.swing.*;
import java.awt.*;

public class SwingSceneRenderer extends JComponent {

    private static final long serialVersionUID = 1L;
    private final SwingFontMetricsService fm = new SwingFontMetricsService();
    private final SwingGraphicsOperations graphicsOperations = new SwingGraphicsOperations();
    private final SceneRenderer renderer;
    private final SceneCreator creator;

    public SwingSceneRenderer(final SceneCreator creator) {
        this.creator = creator;
        renderer = new SceneRenderer(creator, graphicsOperations, fm);
    }

    public SwingSceneRenderer(final SceneElement e) {
        final Scene scene = new Scene(e.getName());
        scene.setSize(e.width, e.height);
        scene.add(e);
        creator = new SceneCreator() {
            @Override
            public Scene getScene() {
                return scene;
            }
        };
        renderer = new SceneRenderer(creator, graphicsOperations, fm);
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
                renderer.render();
                graphicsOperations.setGraphicsContext(null);
                fm.setGraphicsContext(null);
            }
        }
    }
}
