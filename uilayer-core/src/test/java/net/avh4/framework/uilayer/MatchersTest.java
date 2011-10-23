package net.avh4.framework.uilayer;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;

import org.hamcrest.Matcher;
import org.junit.Test;

public class MatchersTest {

	@Test
	public void testPlaceholder() {
		final Matcher<SceneElement> matches = Matchers.placeholder("Item", 20,
				30, 40, 50);
		assertThat(new ScenePlaceholder("Item", 20, 30, 40, 50), matches);
		assertThat(new ScenePlaceholder("Item1", 20, 30, 40, 50), not(matches));
		assertThat(new ScenePlaceholder("Item", 21, 30, 40, 50), not(matches));
		assertThat(new ScenePlaceholder("Item", 20, 31, 40, 50), not(matches));
		assertThat(new ScenePlaceholder("Item", 20, 30, 41, 50), not(matches));
		assertThat(new ScenePlaceholder("Item", 20, 30, 40, 51), not(matches));
		// A custom subclass of SceneElement
		assertThat(new SceneElement("Item", 20, 30, 40, 50) {
		}, not(matches));
	}

}
