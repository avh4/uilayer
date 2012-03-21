package net.avh4.framework.uilayer.android.scene;

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
        // TODO: handle scene size in Android
		scene.setSize(640, 960);
		assertRenderingIsApproved();
	}
}
