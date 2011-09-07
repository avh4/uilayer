package net.avh4.framework.uilayer.swing.scene;

import static org.junit.Assert.fail;

import net.avh4.framework.uilayer.swing.scene.SwingImage;
import net.avh4.framework.uilayer.swing.scene.SwingSceneObject;

import org.junit.Test;

public class SwingImageTest {

	@Test
	public void shouldNotAllowInvalidImageName() {
		try {
			@SuppressWarnings("unused")
			final SwingSceneObject subject = new SwingImage(0, 0, 10, 10,
					"fileDoesNotExist.png");
			fail("Expected IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
			// Pass
		}
	}

}
