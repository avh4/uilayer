package net.avh4.framework.uilayer.mvc;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;

public class MvcUi<M> implements UI {
    private final M model;
    private final View<M> view;
    private final Controller<M> controller;

    public MvcUi(M model, View<M> view, Controller<M> controller) {
        this.controller = controller;
        this.model = model;
        this.view = view;
    }

    public <VC extends View<M> & Controller<M>> MvcUi(M model, VC viewController) {
        this(model, viewController, viewController);
    }

    @Override
    public void click(Rect bounds, Point p) {
        controller.click(model, p);
    }

    @Override
    public void move(Rect bounds, Point p) {
        // TODO
    }

    @Override
    public void key(int keyCode, boolean shift) {
        controller.key(model, keyCode, shift);
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        view.draw(model, bounds, g, fm);
    }

    @Override
    public UpdateAction time() {
        return NEEDS_REDRAW;
    }
}
