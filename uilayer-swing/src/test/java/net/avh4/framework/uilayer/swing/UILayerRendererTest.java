package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@SuppressWarnings("NestedMethodCall")
public class UILayerRendererTest {

    private UILayerRenderer subject;
    private Object sceneCreator;
    private Object sceneElement;
    private Object scene;

    @Before
    public void setup() throws Exception {
        scene = new Scene();
        sceneElement = new ScenePlaceholder("", 0, 0, 100, 100);
        sceneCreator = new SceneCreator() {
            @Override
            public Scene getScene() {
                return null;
            }
        };
        subject = new UILayerRenderer();
    }

    @Test
    public void shouldProvideImagesForSceneCreators() throws Exception {
        assertThat(subject.getImage(sceneCreator), notNullValue());
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
