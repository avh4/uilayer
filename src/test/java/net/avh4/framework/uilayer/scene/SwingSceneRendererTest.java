package net.avh4.framework.uilayer.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SwingSceneRenderer;

import org.junit.Before;
import org.junit.Test;

public class SwingSceneRendererTest {

	private SceneCreator mockCreator;
	private SwingSceneRenderer subject;
	private Scene scenegraph;

	@Before
	public void setUp() {
		scenegraph = new Scene();
		mockCreator = mock(SceneCreator.class);
		stub(mockCreator.getScene()).toReturn(scenegraph);
		subject = new SwingSceneRenderer(mockCreator);
	}

	@Test
	public void testRenderEmptyScene() throws Exception {
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderImage() throws Exception {
		scenegraph.addImage(100, 100, 50, 50, "tile1.png");
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderMultipleImages() throws Exception {
		scenegraph.addImage(100, 100, 150, 150, "tile1.png");
		scenegraph.addImage(150, 150, 75, 75, "tile2.png");
		scenegraph.addImage(100, 300, 16, 16, "tile1.png");
		assertThat(subject, isApproved());
	}

}
