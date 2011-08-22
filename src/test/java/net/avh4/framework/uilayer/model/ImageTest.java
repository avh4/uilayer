package net.avh4.framework.uilayer.model;

import static org.junit.Assert.fail;

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
