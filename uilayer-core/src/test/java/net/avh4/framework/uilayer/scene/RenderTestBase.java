package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.test.TestGraphicsOperations;
import org.junit.Before;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class RenderTestBase {
    private SceneCreator mockCreator;
    private SceneRenderer renderer;
    protected Scene scene;
    private TestGraphicsOperations g;
    private FontMetricsService fm;

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
        g = new TestGraphicsOperations();
        renderer = new SceneRenderer(mockCreator, g, fm);
    }

    protected void assertRenderingIs(String s) throws IOException {
        renderer.render();
        assertThat(g.getRendering(), equalTo(s));
    }
}
