package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.scene.RenderTestBase;

public class RenderSceneTest extends RenderTestBase {

    public void testRenderEmptyScene() throws Exception {
        assertRenderingIsApproved();
    }

    /**
     * If the SceneCreator returns null, we must render something and not throw
     * an exception.
     */
    public void testRenderNullScene() throws Exception {
        scene = null;
        assertRenderingIsApproved();
    }

    public void ignore_testRenderSizedScene() throws Exception {
        // TODO support scene sizes on Android
        scene.setSize(640, 960);
        assertRenderingIsApproved();
    }
}
