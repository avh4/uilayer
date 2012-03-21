package net.avh4.framework.uilayer.swing.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.SceneOval;
import org.junit.Test;

public class RenderOvalTest extends RenderTestBase {

	@Test
	public void testRenderOval() throws Exception {
		scene.add(new SceneOval(50, 50, 200, 500, Color.RED));
		scene.add(new SceneOval(450, 50, 200, 300, Color.YELLOW));
		assertRenderingIsApproved();
	}
}
