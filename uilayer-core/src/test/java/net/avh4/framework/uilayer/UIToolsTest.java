package net.avh4.framework.uilayer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;

import org.junit.Test;

public class UIToolsTest {

	private UI ui;

	@Test
	public void testClickSceneElement() {
		final Scene scene = new Scene();
		scene.add(new ScenePlaceholder("test button", 0, 0, 100, 100));
		ui = mock(UI.class);
		stub(ui.getScene()).toReturn(scene);

		UITools.clickSceneElement(ui, "test button");

		verify(ui).click(50, 50);
	}

}
