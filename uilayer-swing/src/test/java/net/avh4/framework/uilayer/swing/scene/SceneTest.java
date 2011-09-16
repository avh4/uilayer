package net.avh4.framework.uilayer.swing.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.stub;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;

import org.junit.Before;
import org.junit.Test;

public class SceneTest {

	private SceneCreator mockCreator;
	private SwingSceneRenderer subject;
	private Scene scene;

	@Before
	public void setUp() {
		scene = new SwingScene();
		mockCreator = mock(SceneCreator.class);
		stub(mockCreator.getScene()).toReturn(scene);
		subject = new SwingSceneRenderer(mockCreator);
	}

	@Test
	public void testRenderEmptyScene() throws Exception {
		assertThat(subject, isApproved());
	}

	/**
	 * If the SceneCreator returns null, we must render something and not throw
	 * an exception.
	 */
	@Test
	public void testRenderNullScene() throws Exception {
		reset(mockCreator);
		stub(mockCreator.getScene()).toReturn(null);
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderSizedScene() throws Exception {
		scene.setSize(640, 960);
		assertThat(subject, isApproved());
	}
}
