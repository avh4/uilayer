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
}
