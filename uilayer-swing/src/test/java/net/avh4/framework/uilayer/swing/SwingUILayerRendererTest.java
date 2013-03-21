package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@SuppressWarnings("NestedMethodCall")
public class SwingUILayerRendererTest {

    private SwingUILayerRenderer subject;
    private Object sceneElement;
    private Object scene;

    @Before
    public void setup() throws Exception {
        scene = new Scene();
        sceneElement = new ScenePlaceholder("");
        subject = new SwingUILayerRenderer();
    }

    @Test
    public void shouldProvideImagesForSceneElements() throws Exception {
        assertThat(subject.getImage(sceneElement), notNullValue());
    }

    @Test
    public void shouldProvideImagesForScenes() throws Exception {
        assertThat(subject.getImage(scene), notNullValue());
    }
}
