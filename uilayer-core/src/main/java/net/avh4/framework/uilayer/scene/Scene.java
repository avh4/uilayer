package net.avh4.framework.uilayer.scene;

import java.util.ArrayList;
import java.util.Iterator;

public class Scene implements Iterable<SceneElement> {

    private final ArrayList<SceneElement> children = new ArrayList<SceneElement>();
    protected final String title;
    private int width = 800;
    private int height = 600;

    public Scene(final String title) {
        this.title = title;
    }

    public Scene() {
        this("untitled scene");
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * The provided element will be returned for convenience.
     */
    public <T extends SceneElement> T add(final T element) {
        children.add(element);
        return element;
    }

    @Override
    public Iterator<SceneElement> iterator() {
        return children.iterator();
    }

    public SceneElement findSceneElement(String what) {
        for (SceneElement child : children) {
            if (child.getName().equals(what)) {
                return child;
            }
        }
        return null;
    }
}
