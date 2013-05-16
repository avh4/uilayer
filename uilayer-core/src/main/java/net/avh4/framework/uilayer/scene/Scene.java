package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Element;
import net.avh4.math.geometry.Rect;

import java.util.ArrayList;
import java.util.Iterator;

public class Scene implements Iterable<Item>, Element {

    private final ArrayList<Item> children = new ArrayList<Item>();
    protected String title;
    private double width = 800;
    private double height = 600;

    public Scene(final String title) {
        this.title = title;
    }

    public Scene() {
        this("untitled scene");
    }

    public Scene(Item e) {
        this(e.toString());
        this.setTitle(title);
        this.setSize(width, height);
        this.add(e);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public void setSize(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * The provided element will be returned for convenience.
     */
    public Item add(final Item item) {
        children.add(item);
        return item;
    }

    public Item add(Rect bounds, Element element) {
        return add(new Item(bounds, element));
    }

    @Override
    public Iterator<Item> iterator() {
        return children.iterator();
    }

    @SuppressWarnings("unchecked")
    public Item findSceneElement(Object what) {
        for (Item child : children) {
            if (child.element.equals(what)) {
                return child;
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
                .append(", height=").append(height)
                .append(", children=").append(children);
        for (Item child : children) {
            sb.append("\n    ").append(child).append(", ");
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        g.drawRect(bounds, Color.BLACK);

        for (final Item item : children) {
            item.element.draw(item.bounds.scale(Rect.ofSize(800, 600), bounds), g, fm);
        }
    }
}
