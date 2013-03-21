package net.avh4.framework.uilayer.mvc;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.scene.Scene;

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
    public void click(double x, double y) {
        controller.click(model, x, y);
    }

    @Override
    public void key(int keyCode, boolean shift) {
        controller.key(model, keyCode, shift);
    }

    @Override
    public Scene getScene() {
        return view.getScene(model);
    }
}
