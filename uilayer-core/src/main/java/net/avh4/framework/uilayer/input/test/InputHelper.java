package net.avh4.framework.uilayer.input.test;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UIUtil;
import net.avh4.framework.uilayer.mvc.Controller;
import net.avh4.framework.uilayer.mvc.MvcUi;
import net.avh4.framework.uilayer.mvc.View;
import net.avh4.math.Rect;

public class InputHelper {
    private final UI ui;
    private UIUtil uiUtil;

    public InputHelper(UI ui, Rect bounds) {
        this.ui = ui;
        uiUtil = new UIUtil(bounds);
    }

    public <M> InputHelper(M model, View<M> view, Controller<M> controller) {
        this.ui = new MvcUi<M>(model, view, controller);
    }

    public <M, VC extends View<M> & Controller<M>> InputHelper(M model, VC viewController) {
        this(model, viewController, viewController);
    }

    public void clickOnBottom(String goalName) {
        uiUtil.clickOnBottom(ui, goalName);
    }

    public void clickOn(String elementName) {
        uiUtil.clickOn(ui, elementName);
    }

    public void type(String text) {
        KeyReceiverHelper.type(ui, text);
    }
}
