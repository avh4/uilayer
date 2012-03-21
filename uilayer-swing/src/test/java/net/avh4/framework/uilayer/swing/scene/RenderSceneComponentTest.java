package net.avh4.framework.uilayer.swing.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.CompositeSceneElement;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.SceneOval;
import net.avh4.framework.uilayer.scene.SceneRect;

import org.junit.Test;

public class RenderSceneComponentTest extends RenderTestBase {

	/**
	 * This component draws a white square with a red boarder and blue circle.
	 */
	private static class TestComponent extends CompositeSceneElement {

		private final Scene scene;

		public TestComponent(final int x, final int y, final int width,
				final int height) {
			super(null, x, y, width, height);
			scene = new Scene();
			scene.add(new SceneRect(0, 0, 100, 100, Color.RED));
			scene.add(new SceneRect(5, 5, 90, 90, Color.WHITE));
			scene.add(new SceneOval(20, 20, 60, 60, Color.BLUE));
		}

		@Override
		public Iterable<SceneElement> getSceneElements() {
			return scene;
		}
	}

	@Test
	public void testComponent() throws Exception {
		scene.add(new TestComponent(0, 0, 100, 100));
		assertRenderingIsApproved();
	}

	@Test
	public void testTranslatedComponent() throws Exception {
		scene.add(new TestComponent(300, 400, 100, 100));
		assertRenderingIsApproved();
	}

	@Test
	public void testTranslationIsResetAfterDrawingComponent() throws Exception {
		scene.add(new SceneRect(10, 10, 10, 10, Color.YELLOW));
		scene.add(new TestComponent(300, 400, 100, 100));
		scene.add(new SceneRect(20, 20, 10, 10, Color.RED));
		assertRenderingIsApproved();
	}
}
