package net.avh4.framework.uilayer;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import net.avh4.framework.uilayer.model.Scenegraph;

import org.junit.Before;
import org.junit.Test;

public class SwingScenegraphRendererTest {

	private ScenegraphCreator mockCreator;
	private SwingScenegraphRenderer subject;
	private Scenegraph scenegraph;

	@Before
	public void setUp() {
		scenegraph = new Scenegraph();
		mockCreator = mock(ScenegraphCreator.class);
		stub(mockCreator.getScenegraph()).toReturn(scenegraph);
		subject = new SwingScenegraphRenderer(mockCreator);
	}

	@Test
	public void testRenderEmptyScenegraph() throws Exception {
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
