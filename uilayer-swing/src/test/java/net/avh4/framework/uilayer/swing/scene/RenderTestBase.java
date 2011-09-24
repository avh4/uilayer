package net.avh4.framework.uilayer.swing.scene;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import net.avh4.framework.uilayer.SceneCreator;

import org.junit.Before;

public abstract class RenderTestBase {

	private SceneCreator mockCreator;
	protected SwingSceneRenderer subject;
	protected SwingScene scene;

	@Before
	public void setUp() {
		scene = new SwingScene();
		mockCreator = mock(SceneCreator.class);
		stub(mockCreator.getScene()).toReturn(scene);
		subject = new SwingSceneRenderer(mockCreator);
	}

}
