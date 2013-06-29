package net.avh4.framework.uilayer.mvc;

import net.avh4.math.geometry.Point;

public interface Controller<M> {
    void click(M model, Point p);

    void key(M Model, int keyCode, boolean shift);
}
