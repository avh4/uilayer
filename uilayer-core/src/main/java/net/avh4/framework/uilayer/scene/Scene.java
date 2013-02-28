package net.avh4.framework.uilayer.scene;

import java.util.ArrayList;
import java.util.Iterator;

public class Scene implements Iterable<SceneElement> {

    private final ArrayList<SceneElement> children = new ArrayList<SceneElement>();
    protected String title;
    private int width = 800;
    private int height = 600;

    public Scene(final String title) {
        this.title = title;
    }

    public Scene() {
        this("untitled scene");
    }

    public Scene(SceneElement e) {
        this(e.toString());
        String title = e.getName();
        this.setTitle(title);
        int height = e.getHeight() + e.getY();
        int width = e.getWidth() + e.getX();
        this.setSize(width, height);
        this.add(e);
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

    @SuppressWarnings("unchecked")
    public <T extends SceneElement> T findSceneElement(String what) {
        for (SceneElement child : children) {
            String name = child.getName();
            if (name != null && name.equals(what)) {
                return (T) child;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends SceneElement> T findSceneElement(@SuppressWarnings("UnusedParameters") Class<T> elementType, String what) {
        return (T) findSceneElement(what);
    }

    @SuppressWarnings("unchecked")
    public <T extends SceneElement> T findSceneElement(Class<T> elementType) {
        for (SceneElement child : children) {
            if (elementType.isInstance(child)) {
                return (T) child;
            }
        }
        return null;
    }

    public void setTitle(String newTitle) {
        title = newTitle;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder().append("Scene{")
                .append("title='").append(title).append('\'')
                .append(", width=").append(width)
                .append(", height=").append(height);
        sb
                .append(", children=").append(children);
        for (SceneElement child : children) {
            sb.append("\n    ").append(child).append(", ");
        }
        sb.append('}');
        return sb.toString();
    }
}
