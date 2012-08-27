package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.SceneCreator;
import org.junit.Before;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static net.avh4.util.imagecomparison.ImageComparisonMatchers.isApproved;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public abstract class RenderTestBase {

    private SceneCreator mockCreator;
    protected SwingSceneRenderer subject;
    protected Scene scene;

    @Before
    public void setUp() {
        scene = new Scene();
        mockCreator = mock(SceneCreator.class);
        stub(mockCreator.getScene()).toAnswer(new Answer<Scene>() {
            @Override
            public Scene answer(InvocationOnMock invocation) throws Throwable {
                return scene;
            }
        });
        SwingGraphicsOperations graphicsOperations = new SwingGraphicsOperations();
        subject = new SwingSceneRenderer(graphicsOperations, new SceneRenderer(mockCreator));
    }

    protected void assertRenderingIsApproved() throws IOException {
        assertThat(subject, isApproved());
    }
}
