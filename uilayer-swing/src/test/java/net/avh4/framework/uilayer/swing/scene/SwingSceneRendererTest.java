package net.avh4.framework.uilayer.swing.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.scene.SwingSceneRenderer;

import org.junit.Test;

public class SwingSceneRendererTest {

	@Test
	public void testRenderingASingleSceneElement() throws Exception {
		final SceneElement e = new ScenePlaceholder("Element", 0, 0, 100, 100);
		final SwingSceneRenderer subject = new SwingSceneRenderer(e);
		assertThat(subject, isApproved());
	}

}
