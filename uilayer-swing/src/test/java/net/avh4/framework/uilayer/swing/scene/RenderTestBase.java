package net.avh4.framework.uilayer.swing.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import java.io.IOException;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SwingSceneRenderer;

import org.junit.Before;

public abstract class RenderTestBase {

	private SceneCreator mockCreator;
	protected SwingSceneRenderer subject;
	protected Scene scene;

	@Before
	public void setUp() {
		scene = new Scene();
		mockCreator = mock(SceneCreator.class);
		stub(mockCreator.getScene()).toReturn(scene);
		subject = new SwingSceneRenderer(mockCreator);
	}

	protected void assertRenderingIsApproved() throws IOException {
		assertThat(subject, isApproved());
	}

}
