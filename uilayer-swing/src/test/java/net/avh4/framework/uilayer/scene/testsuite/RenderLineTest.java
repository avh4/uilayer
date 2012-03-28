package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.scene.SceneLine;

public class RenderLineTest extends RenderTestBase {

	public void testRenderLine() throws Exception {
		scene.add(new SceneLine(Color.WHITE, 100, 100, 200, 200));
		scene.add(new SceneLine(Color.YELLOW, 0, 300, 800, 0));
		assertRenderingIsApproved();
	}
}
