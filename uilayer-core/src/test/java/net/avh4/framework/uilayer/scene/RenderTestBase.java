package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.test.TestGraphicsOperations;
import net.avh4.math.Rect;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.mockito.Mockito;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("NestedMethodCall")
public class RenderTestBase {
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
    }

    protected void assertRenderingIs(String s) {
        assertThat(g.getRendering(), equalTo(s));
    }

    protected void assertRendering(Matcher<String> matcher) {
        assertThat(g.getRendering(), matcher);
    }

    protected void assertRenderingOf(Rect bounds, Element subject, String s) throws IOException {
        subject.draw(bounds, g, fm);
        assertRenderingIs(s);
    }

    protected void draw(Rect bounds, Element element) {
        element.draw(bounds, g, fm);
    }
}
