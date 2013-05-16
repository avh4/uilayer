package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UIUtilTest {

    private UIUtil subject;
    private Scene scene;
    private UI ui;
    private Rect bounds;

    @Before
    public void setup() throws Exception {
        scene = new Scene();
        scene.add(Rect.fromTopLeft(150, 200, 100, 30), new ScenePlaceholder("Button1"));

        ui = mock(UI.class);
        stub(ui.getScene()).toReturn(scene);

        bounds = Rect.fromTopLeft(0, 0, 800, 600);
        subject = new UIUtil(bounds);
    }

    @Test
    public void clickOn_clicksTheCenterOfTheElement() throws Exception {
        subject.clickOn(ui, new ScenePlaceholder("Button1"));
        verify(ui).click(bounds, 200, 215);
    }

    @Test
    public void clickOnTop_clicksTheTopOfTheElement() throws Exception {
        subject.clickOnTop(ui, new ScenePlaceholder("Button1"));
        verify(ui).click(bounds, 200, 201);
    }

    @Test
    public void clickOnBottom_clicksTheBottomOfTheElement() throws Exception {
        subject.clickOnBottom(ui, new ScenePlaceholder("Button1"));
        verify(ui).click(bounds, 200, 229);
    }
}
