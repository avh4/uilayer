package net.avh4.framework.uilayer.swing.scene;

import static org.junit.Assert.fail;

import java.awt.Graphics;

import net.avh4.framework.uilayer.scene.SceneObject;

import org.junit.Test;

public class SwingImageTest {

	@Test
	public void shouldNotAllowInvalidImageName() {
		try {
			@SuppressWarnings("unused")
			final SceneObject<Graphics> subject = new SwingImage(0, 0, 10, 10,
					"fileDoesNotExist.png");
			fail("Expected IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
			// Pass
		}
	}

}
