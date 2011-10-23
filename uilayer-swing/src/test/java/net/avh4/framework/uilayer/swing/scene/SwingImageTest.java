package net.avh4.framework.uilayer.swing.scene;

import static org.junit.Assert.fail;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.SceneImage;

import org.junit.Test;

public class SwingImageTest {

	@Test
	public void shouldNotAllowInvalidImageName() {
		try {
			@SuppressWarnings("unused")
			final SceneElement subject = new SceneImage(0, 0, 10, 10,
					"fileDoesNotExist.png");
			fail("Expected IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
			// Pass
		}
	}

}
