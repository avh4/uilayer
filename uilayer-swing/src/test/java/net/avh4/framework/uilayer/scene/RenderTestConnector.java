package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.SceneCreator;

import java.io.IOException;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class RenderTestConnector {

    public void assertRenderingIsApproved(Scene scene) {
        final SceneCreator mockCreator = mock(SceneCreator.class);
        stub(mockCreator.getScene()).toReturn(scene);
        final SwingSceneRenderer subject = new SwingSceneRenderer(mockCreator);
        try {
            assertThat(subject, isApproved());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
