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

	@Test
	public void testRenderText() throws Exception {
		final String longString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		scenegraph.addPlaceholder("Text box: size 36", 50, 50, 200, 1000);
		scenegraph.addText(longString, 50, 50, 200, "Tuffy.ttf", 36);
		scenegraph.addPlaceholder("Text box: size 12", 450, 50, 200, 1000);
		scenegraph.addText(longString, 450, 50, 200, "Tuffy.ttf", 12);
		assertThat(subject, isApproved());
	}

}
