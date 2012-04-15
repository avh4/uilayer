package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

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

    @Test
    public void findSceneElement_withOneElement_shouldReturnMatchingElement() throws Exception {
        final ScenePlaceholder element = new ScenePlaceholder("Needle", 0, 0, 0, 0);
        subject.add(element);

        assertThat(subject.findSceneElement("Needle"), sameInstance((SceneElement) element));
    }

    @Test
    public void findSceneElement_withEmptyScene_shouldReturnNull() throws Exception {
        assertThat(subject.findSceneElement("Needle"), nullValue());
    }

    @Test
    public void findSceneElement_withTwoElements_shouldReturnMatchingElement() throws Exception {
        final ScenePlaceholder element = new ScenePlaceholder("Needle", 0, 0, 0, 0);
        subject.add(new ScenePlaceholder("Red Herring", 0, 0, 0, 0));
        subject.add(element);

        assertThat(subject.findSceneElement("Needle"), sameInstance((SceneElement) element));
    }
}
