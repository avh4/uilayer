package net.avh4.framework.uilayer.swing.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.SceneRect;

import org.junit.Test;

public class RenderRectTest extends RenderTestBase {

	@Test
	public void testRenderRect() throws Exception {
		scene.add(new SceneRect(50, 50, 200, 500, Color.RED));
		scene.add(new SceneRect(450, 50, 200, 300, Color.YELLOW));
		assertThat(subject, isApproved());
	}
}
