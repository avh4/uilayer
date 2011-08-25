package net.avh4.framework.uilayer.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.stub;
import net.avh4.framework.uilayer.SceneCreator;

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
	public void testRenderPlaceholders() throws Exception {
		scenegraph.addPlaceholder("Background", 0, 0, 800, 600);
		scenegraph.addPlaceholder("Body", 20, 20, 100, 560);
		assertThat(subject, isApproved());
	}

}
