package net.avh4.framework.uilayer.scene;

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

	public void testRenderSizedScene() throws Exception {
		scene.setSize(640, 960);
        assertRenderingIsApproved();
	}
}
