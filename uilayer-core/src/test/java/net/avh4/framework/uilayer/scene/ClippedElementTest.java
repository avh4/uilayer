package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

@SuppressWarnings({"ChainedMethodCall", "NestedMethodCall"})
public class ClippedElementTest extends RenderTestBase {

    private ClippedElementDelegate delegate;
    private ClippedElementDelegate delegate2;
    private ClippedElement subject;
    private GraphicsOperations g;
    private FontMetricsService fm;
    private final Rect boundsAtOrigin = Rect.fromTopLeft(0, 0, 320, 640);
    private final Rect boundsAtXY = Rect.fromTopLeft(25, 50, 320, 640);
    private final Rect boundsSmallerThanMap = Rect.fromTopLeft(0, 0, 160, 96);

    @Before
    public void setup() throws Exception {
        g = Mockito.mock(GraphicsOperations.class);
        fm = Mockito.mock(FontMetricsService.class);
        delegate = makeMockDelegate("Delegate");
        delegate2 = makeMockDelegate("Delegate 2");
        subject = new ClippedElement(delegate);
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
    public void shouldDrawTheTiles() throws Exception {
        draw(boundsAtOrigin, subject);
        assertRenderingIs("" +
                "=== TRANSLATE to 0, 0 ===\n" +
                "Text: \"Delegate\" 0.0, 0.0 Font{'Pfennig.ttf' (12)} 0xff000000\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }

    @Test
    public void shouldDrawAtTheCorrectCoordinates() throws Exception {
        draw(boundsAtXY, subject);
        assertRenderingIs("" +
                "=== TRANSLATE to 25, 50 ===\n" +
                "Text: \"Delegate\" 0.0, 0.0 Font{'Pfennig.ttf' (12)} 0xff000000\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }

    @Test
    public void shouldClipMapToDimensions() throws Exception {
        subject.draw(boundsSmallerThanMap, g, fm);
        Mockito.verify(delegate).draw(g, fm, 0, 0, 160, 96);
    }

    @Test
    public void shouldClipMapFromCorrectOrigin() throws Exception {
        subject.setClipPosition(15, 30);
        subject.draw(boundsSmallerThanMap, g, fm);
        Mockito.verify(delegate).draw(g, fm, 15, 30, 160, 96);
    }

    @Test
    public void setDelegate_shouldChangeTheDelegate() throws Exception {
        subject.setDelegate(delegate2);
        draw(boundsAtOrigin, subject);
        assertRenderingIs("" +
                "=== TRANSLATE to 0, 0 ===\n" +
                "Text: \"Delegate 2\" 0.0, 0.0 Font{'Pfennig.ttf' (12)} 0xff000000\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }
}
