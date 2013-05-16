package net.avh4.framework.uilayer.input;

import net.avh4.math.geometry.Rect;

public interface ClickReceiver {

    void click(Rect bounds, double x, double y);
}
