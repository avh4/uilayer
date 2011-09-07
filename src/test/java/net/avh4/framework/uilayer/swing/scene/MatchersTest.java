package net.avh4.framework.uilayer.swing.scene;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.awt.Graphics;

import net.avh4.framework.uilayer.swing.scene.SwingPlaceholder;
import net.avh4.framework.uilayer.swing.scene.SwingSceneObject;

import org.hamcrest.Matcher;
import org.junit.Test;

public class MatchersTest {

	@Test
	public void testPlaceholder() {
		final Matcher<SwingSceneObject> matches = Matchers.placeholder("Item", 20,
				30, 40, 50);
		assertThat(new SwingPlaceholder("Item", 20, 30, 40, 50), matches);
		assertThat(new SwingPlaceholder("Item1", 20, 30, 40, 50), not(matches));
		assertThat(new SwingPlaceholder("Item", 21, 30, 40, 50), not(matches));
		assertThat(new SwingPlaceholder("Item", 20, 31, 40, 50), not(matches));
		assertThat(new SwingPlaceholder("Item", 20, 30, 41, 50), not(matches));
		assertThat(new SwingPlaceholder("Item", 20, 30, 40, 51), not(matches));
		assertThat(new SwingSceneObject(20, 30, 40, 50) {
			@Override
			public void draw(final Graphics g) {
			}
		}, not(matches));
	}

}
