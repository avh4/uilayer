package net.avh4.framework.uilayer.scene;

import static org.junit.Assert.fail;

import net.avh4.framework.uilayer.scene.Image;

import org.junit.Test;

public class ImageTest {

	@Test
	public void shouldNotAllowInvalidImageName() {
		try {
			@SuppressWarnings("unused")
			final Image subject = new Image(0, 0, 10, 10,
					"fileDoesNotExist.png");
			fail("Expected IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
			// Pass
		}

	}

}
