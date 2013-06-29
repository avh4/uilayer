package net.avh4.framework.uilayer.layout;

import net.avh4.math.geometry.Rect;

public interface BoundsTransformation {
    public Rect apply(Rect bounds);
}
