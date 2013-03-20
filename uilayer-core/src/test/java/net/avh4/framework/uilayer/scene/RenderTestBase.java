package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.test.TestGraphicsOperations;
import net.avh4.math.Rect;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

@SuppressWarnings("NestedMethodCall")
public class RenderTestBase {
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
        SceneCreator sceneCreator = mock(SceneCreator.class);
        stub(sceneCreator.getScene()).toAnswer(new Answer<Scene>() {
            @Override
            public Scene answer(InvocationOnMock invocation) throws Throwable {
                return scene;
            }
        });
        renderer = new SceneRenderer(sceneCreator);
    }

    protected void assertRenderingIs(String s) {
        assertThat(g.getRendering(), equalTo(s));
    }

    protected void assertRendering(Matcher<String> matcher) {
        assertThat(g.getRendering(), matcher);
    }

    protected void assertRenderingOfSceneIs(String s) throws IOException {
        renderer.render(scene.getWidth(), scene.getHeight(), g, fm);
        assertRenderingIs(s);
    }

    protected void assertRenderingOf(Object o, String s) throws IOException {
        if (o instanceof Element && o instanceof SceneElement) {
            SceneElement subject = (SceneElement) o;
            ((Element) subject).draw(new Rect(subject.getX(), subject.getY(), subject.getWidth(), subject.getHeight()), g, fm);
        } else if (o instanceof Element) {
            throw new RuntimeException("Not yet implemented -- what bounds should we use to draw?");
        } else if (o instanceof SceneElement) {
            ((SceneElement) o).draw(g, fm);
        }
        assertRenderingIs(s);
    }
}
