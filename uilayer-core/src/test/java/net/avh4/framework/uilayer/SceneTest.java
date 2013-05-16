package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Item;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.geometry.Rect;
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
        subjectWithElement = new Scene(new Item(Rect.fromTopLeft(0, 0, 50, 60), new ScenePlaceholder("")));
        subjectWithOffsetElement = new Scene(new Item(Rect.fromTopLeft(5, 5, 50, 60), new ScenePlaceholder("")));
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
        subject.add(Rect.fromTopLeft(0, 0, 800, 600), new ScenePlaceholder("Item 1"));
        assertThat(subject, hasItem(new Item(Rect.fromTopLeft(0, 0, 800, 600), new ScenePlaceholder("Item 1"))));
    }

    @Test
    public void findSceneElement_withOneElement_shouldReturnMatchingElement() throws Exception {
        final ScenePlaceholder element = new ScenePlaceholder("Needle");
        subject.add(Rect.fromTopLeft(0, 0, 0, 0), element);

        assertThat(subject.findSceneElement(element).element, sameInstance((Element) element));
    }

    @Test
    public void findSceneElement_withEmptyScene_shouldReturnNull() throws Exception {
        assertThat(subject.findSceneElement("Needle"), nullValue());
    }

    @Test
    public void findSceneElement_withTwoElements_shouldReturnMatchingElement() throws Exception {
        final ScenePlaceholder element = new ScenePlaceholder("Needle");
        subject.add(Rect.fromTopLeft(0, 0, 0, 0), new ScenePlaceholder("Red Herring"));
        subject.add(Rect.fromTopLeft(0, 0, 0, 0), element);

        assertThat(subject.findSceneElement(element).element, sameInstance((Element) element));
    }

    @Test
    public void findSceneElement_whenSceneHasElementWithNullName() {
        assertThat(subject.findSceneElement(null), nullValue());
    }
}
