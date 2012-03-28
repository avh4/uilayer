package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.scene.SceneImage;

public class RenderImagesTest extends RenderTestBase {

	public void testRenderResourceImage() throws Exception {
		scene.add(new SceneImage(100, 100, 50, 50, "tile1.png"));
        assertRenderingIsApproved();
	}

	public void testRenderMultipleImages() throws Exception {
		scene.add(new SceneImage(100, 100, 150, 150, "tile1.png"));
		scene.add(new SceneImage(150, 150, 75, 75, "tile2.png"));
		scene.add(new SceneImage(100, 300, 16, 16, "tile1.png"));
        assertRenderingIsApproved();
	}

	public void testRenderRepositionedImage() throws Exception {
		final SceneImage image = scene.add(new SceneImage(100, 100, 50, 50,
				"tile1.png"));
		image.setPosition(0, 0);
        assertRenderingIsApproved();
	}

	public void testRenderClippedImage() throws Exception {
		scene.add(new SceneImage(100, 100, 50, 50, "tile1.png", 0, 0, 25, 25));
        assertRenderingIsApproved();
	}

	public void testRenderReclippedClippedImage() throws Exception {
		final SceneImage image = scene.add(new SceneImage(100, 100, 50, 50,
				"tile1.png", 0, 0, 25, 25));
		image.setClipPosition(25, 25);
        assertRenderingIsApproved();
	}
}
