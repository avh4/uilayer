package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.math.Rect;

public class Item {
    public final Rect bounds;
    public final Element element;

    public Item(Rect bounds, Element element) {
        this.bounds = bounds;
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (bounds != null ? !bounds.equals(item.bounds) : item.bounds != null) return false;
        if (element != null ? !element.equals(item.element) : item.element != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bounds != null ? bounds.hashCode() : 0;
        result = 31 * result + (element != null ? element.hashCode() : 0);
        return result;
    }
}
