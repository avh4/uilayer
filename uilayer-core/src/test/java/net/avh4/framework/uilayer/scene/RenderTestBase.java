package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.test.TestGraphicsOperations;
import org.junit.Before;
import org.mockito.Mockito;
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
    protected TestGraphicsOperations g;
    protected FontMetricsService fm;

    @Before
    public void setUp() throws Exception {
        g = new TestGraphicsOperations();
        fm = Mockito.mock(FontMetricsService.class);
        Mockito.stub(fm.getAscent(Font.PFENNIG.size(64))).toReturn(68.0f);
        Mockito.stub(fm.getDescent(Font.PFENNIG.size(64))).toReturn(17.0f);
        Mockito.stub(fm.stringWidth(Font.PFENNIG.size(64), "CENTER")).toReturn(231.0f);
        Mockito.stub(fm.stringWidth(Font.PFENNIG.size(64), "A")).toReturn(41.0f);
        Mockito.stub(fm.stringWidth(Font.PFENNIG.size(64), "a")).toReturn(30.0f);
        Mockito.stub(fm.stringWidth(new Font("Tuffy.ttf", 12), "Red Point")).toReturn(47.0f);
        Mockito.stub(fm.getLineHeight(new Font("Tuffy.ttf", 12))).toReturn(17.0f);
        scene = new Scene();
        mockCreator = mock(SceneCreator.class);
        stub(mockCreator.getScene()).toAnswer(new Answer<Scene>() {
            @Override
            public Scene answer(InvocationOnMock invocation) throws Throwable {
                return scene;
            }
        });
        renderer = new SceneRenderer(mockCreator);
    }

    protected void assertRenderingIs(String s) throws IOException {
        renderer.render(g, fm);
        assertThat(g.getRendering(), equalTo(s));
    }

    protected void assertRenderingOf(SceneElement subject, String s) throws IOException {
        subject.draw(g, fm);
        assertThat(g.getRendering(), equalTo(s));
    }
}
