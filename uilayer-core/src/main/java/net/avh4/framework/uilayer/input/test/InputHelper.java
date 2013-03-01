package net.avh4.framework.uilayer.input.test;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UIUtil;

public class InputHelper {
    private final UI ui;

    public InputHelper(UI ui) {
        this.ui = ui;
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
