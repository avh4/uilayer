package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.test.categories.FontRendering;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RenderSceneTest extends RenderTestBase {

    @Test
    public void testRenderEmptyScene() throws Exception {
        assertRenderingIsApproved();
    }

    /**
     * If the SceneCreator returns null, we must render something and not throw
     * an exception.
     */
    @Test
    @Category(FontRendering.class)
    public void testRenderNullScene() throws Exception {
        scene = null;
        assertRenderingIsApproved();
    }

    @Test
    public void testRenderSizedScene() throws Exception {
        scene.setSize(640, 960);
        assertRenderingIsApproved();
    }
}
