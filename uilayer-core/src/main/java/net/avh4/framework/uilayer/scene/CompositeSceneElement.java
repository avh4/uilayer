package net.avh4.framework.uilayer.scene;

public abstract class CompositeSceneElement extends SceneElement {

    public CompositeSceneElement(final String name, final int x, final int y,
                                 final int width, final int height) {
        super(name, x, y, width, height);
    }

    public abstract Iterable<SceneElement> getSceneElements();

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
        g.translate(x, y);
        for (final SceneElement object : getSceneElements()) {
            SceneRenderer.draw(object, g, fm);
        }
        g.translate(-x, -y);
    }
}
