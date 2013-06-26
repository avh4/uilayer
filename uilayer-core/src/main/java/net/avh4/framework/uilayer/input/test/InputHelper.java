package net.avh4.framework.uilayer.input.test;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.mvc.Controller;
import net.avh4.framework.uilayer.mvc.MvcUi;
import net.avh4.framework.uilayer.mvc.View;
import net.avh4.math.geometry.Rect;

public class InputHelper {
    private final UI ui;

    public InputHelper(UI ui, Rect bounds) {
        this.ui = ui;
    }

    public <M> InputHelper(M model, View<M> view, Controller<M> controller) {
        this.ui = new MvcUi<M>(model, view, controller);
    }

    public <M, VC extends View<M> & Controller<M>> InputHelper(M model, VC viewController) {
        this(model, viewController, viewController);
    }

    public void type(String text) {
        KeyReceiverHelper.type(ui, text);
    }
}
