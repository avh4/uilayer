package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class UIUtilTest {

    private Scene scene;
    private UI ui;

    @Before
    public void setup() throws Exception {
        scene = new Scene();
        scene.add(new ScenePlaceholder("Button1", 150, 200, 100, 30));

        ui = mock(UI.class);
        stub(ui.getScene()).toReturn(scene);
    }

    @Test
    public void clickOn_clicksTheCenterOfTheElement() throws Exception {
        UIUtil.clickOn(ui, "Button1");
        verify(ui).click(200, 215);
    }

    @Test
    public void clickOnTop_clicksTheTopOfTheElement() throws Exception {
        UIUtil.clickOnTop(ui, "Button1");
        verify(ui).click(200, 201);
    }

    @Test
    public void clickOnBottom_clicksTheBottomOfTheElement() throws Exception {
        UIUtil.clickOnBottom(ui, "Button1");
        verify(ui).click(200, 229);
    }
}
