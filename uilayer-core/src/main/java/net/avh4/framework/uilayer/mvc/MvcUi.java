package net.avh4.framework.uilayer.mvc;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.scene.Scene;

public class MvcUi<M> implements UI {
    private final Controller<M> controller;
    private final M model;
    private final View<M> view;

    public MvcUi(Controller<M> controller, M model, View<M> view) {
        this.controller = controller;
        this.model = model;
        this.view = view;
    }

    @Override
    public void click(int x, int y) {
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
