package net.avh4.framework.uilayer.scene;

import org.junit.Test;

public class SceneRendererTest extends RenderTestBase {

    @Test
    public void testRenderEmptyScene() throws Exception {
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n");
    }

    @Test
    public void testRenderNullScene() throws Exception {
        scene = null;
        assertRenderingIs("" +
                "Rectangle: 0, 0, 100, 100, 0xff7f7f7f\n" +
                "Text: \"(No scene)\" 0.0, 20.0 Font{'Pfennig.ttf' (12)} 0xff000000\n");
    }

    @Test
    public void testRenderSizedScene() throws Exception {
        scene.setSize(640, 960);
        assertRenderingIs("Rectangle: 0, 0, 640, 960, 0xff000000\n");
    }

    @Test
    public void testHiddenElement() throws Exception {
        final ScenePlaceholder hidden = new ScenePlaceholder("Hidden", 100, 100, 200, 200);
        hidden.setHidden(true);
        scene.add(hidden);
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n");
    }
}
