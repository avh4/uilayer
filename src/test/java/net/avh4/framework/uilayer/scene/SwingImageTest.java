package net.avh4.framework.uilayer.scene;

import static org.junit.Assert.fail;

import org.junit.Test;

public class SwingImageTest {

	@Test
	public void shouldNotAllowInvalidImageName() {
		try {
			@SuppressWarnings("unused")
			final SwingImage subject = new SwingImage(0, 0, 10, 10,
					"fileDoesNotExist.png");
			fail("Expected IllegalArgumentException");
		} catch (final IllegalArgumentException e) {
			// Pass
		}

	}

}
