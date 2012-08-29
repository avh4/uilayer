package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

@SuppressWarnings("NestedMethodCall")
public class SceneTest {

    private Scene subject;
    private Scene subjectWithElement;
    private Scene subjectWithOffsetElement;

    @Before
    public void setUp() {
        subject = new Scene();
        subjectWithElement = new Scene(new ScenePlaceholder("", 0, 0, 50, 60));
        subjectWithOffsetElement = new Scene(new ScenePlaceholder("", 5, 5, 50, 60));
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
    public void testSetTitle() throws Exception {
        final Scene subject = new Scene("Original Title");
        subject.setTitle("New Title");
        assertThat(subject.getTitle(), is("New Title"));
    }

    @Test
    public void testCanAddPlaceholder() {
        subject.add(new ScenePlaceholder("Item 1", 0, 0, 800, 600));
        assertThat(subject, hasItem(Matchers.placeholder("Item 1", 0, 0, 800, 600)));
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

    @Test
    public void findSceneElement_shouldReturnRequestedType() throws Exception {
        final ScenePlaceholder element = new ScenePlaceholder("Needle", 0, 0, 0,
                0);
        subject.add(element);

        final ScenePlaceholder actual = subject
                .findSceneElement(ScenePlaceholder.class, "Needle");
        assertThat(actual, sameInstance(element));
    }

    @Test
    public void sceneWithElement_shouldHaveDimensions() throws Exception {
        assertThat(subjectWithElement.getWidth(), is(50));
        assertThat(subjectWithElement.getHeight(), is(60));
    }

    @Test
    public void sceneWithElement_shouldIncludeCoordinateOffsetInDimensions() throws Exception {
        assertThat(subjectWithOffsetElement.getWidth(), is(55));
        assertThat(subjectWithOffsetElement.getHeight(), is(65));
    }
}
