package net.avh4.framework.uilayer.scene;

import org.junit.Test;

public class SceneRendererTest extends RenderTestBase {

    @Test
    public void testRenderEmptyScene() throws Exception {
        assertRenderingOfSceneIs("Rectangle: 0, 0, 800, 600, 0xff000000\n");
    }

    @Test
    public void testRenderSizedScene() throws Exception {
        scene.setSize(640, 960);
        assertRenderingOfSceneIs("Rectangle: 0, 0, 640, 960, 0xff000000\n");
    }

    @Test
    public void testHiddenElement() throws Exception {
        final ScenePlaceholder hidden = new ScenePlaceholder("Hidden", 100, 100, 200, 200);
        hidden.setHidden(true);
        scene.add(hidden);
        assertRenderingOfSceneIs("Rectangle: 0, 0, 800, 600, 0xff000000\n");
    }
}
