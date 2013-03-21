package net.avh4.framework.uilayer.mvc;

public interface Controller<M> {
    void click(M model, double x, double y);

    void key(M Model, int keyCode, boolean shift);
}
