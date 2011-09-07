package net.avh4.framework.uilayer.swing.scene;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import net.avh4.framework.uilayer.swing.scene.SwingScene;

import org.junit.Before;
import org.junit.Test;

public class SwingSceneTest {

	private SwingScene subject;

	@Before
	public void setUp() {
		subject = new SwingScene();
	}

	@Test
	public void testDefaultTitle() {
		final SwingScene subject = new SwingScene();
		assertThat(subject.getTitle(), is("Untitled Scene"));
	}

	@Test
	public void testTitle() {
		final SwingScene subject = new SwingScene("My Title");
		assertThat(subject.getTitle(), is("My Title"));
	}

	@Test
	public void testCanAddPlaceholder() {
		subject.addPlaceholder("Item 1", 0, 0, 800, 600);
		assertThat(subject,
				hasItem(Matchers.placeholder("Item 1", 0, 0, 800, 600)));
	}

}
