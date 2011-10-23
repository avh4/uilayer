package net.avh4.framework.uilayer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;

import org.junit.Before;
import org.junit.Test;

public class SceneTest {

	private Scene subject;

	@Before
	public void setUp() {
		subject = new Scene();
	}

	@Test
	public void testDefaultTitle() {
		final Scene subject = new Scene();
		assertThat(subject.getTitle(), is("untitled scene"));
	}

	@Test
	public void testTitle() {
		final Scene subject = new Scene("My Title");
		assertThat(subject.getTitle(), is("My Title"));
	}

	@Test
	public void testCanAddPlaceholder() {
		subject.add(new ScenePlaceholder("Item 1", 0, 0, 800, 600));
		assertThat(subject,
				hasItem(Matchers.placeholder("Item 1", 0, 0, 800, 600)));
	}

}
