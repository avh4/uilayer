package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SuppressWarnings({"ChainedMethodCall", "NestedMethodCall", "LawOfDemeter"})
public class ClippedElementTest extends RenderTestBase {

    private ClippedElementDelegate delegate;
    private ClippedElementDelegate delegate2;
    private ClippedElement subjectAtOrigin;
    private ClippedElement subjectAtXY;
    private ClippedElement subjectSmallerThanMap;
    private GraphicsOperations g;

    @Before
    public void setup() throws Exception {
        g = Mockito.mock(GraphicsOperations.class);
        delegate = makeMockDelegate("Delegate");
        delegate2 = makeMockDelegate("Delegate 2");
        subjectAtOrigin = new ClippedElement(delegate, 0, 0, 320, 640);
        subjectAtXY = new ClippedElement(delegate, 25, 50, 320, 640);
        subjectSmallerThanMap = new ClippedElement(delegate, 0, 0, 160, 96);
    }

    private ClippedElementDelegate makeMockDelegate(final String name) {
        ClippedElementDelegate delegate = Mockito.mock(ClippedElementDelegate.class, name);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                GraphicsOperations g = (GraphicsOperations) invocation.getArguments()[0];
                g.drawText(name, 0, 0, Font.PFENNIG, Color.BLACK);
                return null;
            }
        }).when(delegate).draw(Mockito.any(GraphicsOperations.class), Mockito.any(FontMetricsService.class),
                Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
        return delegate;
    }

    @Test
    public void shouldHaveWidthFromTheMapAndTileSize() throws Exception {
        assertThat(subjectAtOrigin.getWidth(), is(320.));
    }

    @Test
    public void shouldHaveHeightFromTheMapAndTileSize() throws Exception {
        assertThat(subjectAtOrigin.getHeight(), is(640.));
    }

    @Test
    public void shouldDrawTheTiles() throws Exception {
        assertRenderingOf(subjectAtOrigin, "=== TRANSLATE to 0, 0 ===\n" +
                "Text: \"Delegate\" 0.0, 0.0 Font{'Pfennig.ttf' (12)} 0xff000000\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }

    @Test
    public void shouldHaveCoordinates() throws Exception {
        assertThat(subjectAtXY.getX(), is(25.));
        assertThat(subjectAtXY.getY(), is(50.));
    }

    @Test
    public void shouldDrawAtTheCorrectCoordinates() throws Exception {
        assertRenderingOf(subjectAtXY, "=== TRANSLATE to 25, 50 ===\n" +
                "Text: \"Delegate\" 0.0, 0.0 Font{'Pfennig.ttf' (12)} 0xff000000\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }

    @Test
    public void shouldHaveDimensions() throws Exception {
        assertThat(subjectSmallerThanMap.getWidth(), is(160.));
        assertThat(subjectSmallerThanMap.getHeight(), is(96.));
    }

    @Test
    public void shouldClipMapToDimensions() throws Exception {
        subjectSmallerThanMap.draw(g, null);
        Mockito.verify(delegate).draw(g, null, 0, 0, 160, 96);
    }

    @Test
    public void shouldClipMapFromCorrectOrigin() throws Exception {
        subjectSmallerThanMap.setClipPosition(15, 30);
        subjectSmallerThanMap.draw(g, null);
        Mockito.verify(delegate).draw(g, null, 15, 30, 160, 96);
    }

    @Test
    public void setDelegate_shouldChangeTheDelegate() throws Exception {
        subjectAtOrigin.setDelegate(delegate2);
        assertRenderingOf(subjectAtOrigin, "=== TRANSLATE to 0, 0 ===\n" +
                "Text: \"Delegate 2\" 0.0, 0.0 Font{'Pfennig.ttf' (12)} 0xff000000\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }
}
