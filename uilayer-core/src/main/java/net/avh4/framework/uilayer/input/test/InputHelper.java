package net.avh4.framework.uilayer.input.test;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UIUtil;
import net.avh4.framework.uilayer.mvc.Controller;
import net.avh4.framework.uilayer.mvc.MvcUi;

public class InputHelper {
    private final UI ui;

    public InputHelper(UI ui) {
        this.ui = ui;
    }

    public <M> InputHelper(M model, Controller<M> controller) {
        this.ui = new MvcUi<M>(model, null, controller);
    }

    public void clickOnBottom(String goalName) {
        UIUtil.clickOnBottom(ui, goalName);
    }

    public void clickOn(String elementName) {
        UIUtil.clickOn(ui, elementName);
    }

    public void type(String text) {
        KeyReceiverHelper.type(ui, text);
    }
}
