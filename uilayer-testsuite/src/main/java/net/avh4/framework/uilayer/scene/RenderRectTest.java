package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;

public class RenderRectTest extends RenderTestBase {

	public void testRenderRect() throws Exception {
		scene.add(new SceneRect(50, 50, 200, 500, Color.RED));
		scene.add(new SceneRect(450, 50, 200, 300, Color.YELLOW));
		assertRenderingIsApproved();
	}
}
