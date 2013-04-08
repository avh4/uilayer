package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ElementBoundsAnimatorTest {
    private ElementBoundsAnimator subject;
    @Mock
    private Element e1;
    @Mock
    private Element e2;
    @Mock
    private GraphicsOperations g;
    @Mock
    private FontMetricsService fm;
    @Mock
    private TimeService timeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new ElementBoundsAnimator(timeService);
        stub(timeService.nowMs()).toReturn(0l);
    }

    @Test
    public void shouldRenderOneElement() {
        Rect bounds = Rect.fromTopLeft(1, 2, 10, 20);
        subject.updateElement(bounds, e1);

        subject.render(g, fm);

        verify(e1).draw(bounds, g, fm);
    }

    @Test
    public void shouldRenderManyElements() {
        Rect bounds1 = Rect.fromTopLeft(1, 2, 10, 20);
        Rect bounds2 = Rect.fromTopLeft(3, 4, 30, 40);
        subject.updateElement(bounds1, e1);
        subject.updateElement(bounds2, e2);

        subject.render(g, fm);

        verify(e1).draw(bounds1, g, fm);
        verify(e2).draw(bounds2, g, fm);
    }

    @Test
    public void shouldRenderAnUpdatedElement() {
        Rect oldBounds = Rect.fromTopLeft(1, 2, 10, 20);
        Rect newBounds = Rect.fromTopLeft(5, 6, 50, 60);
        subject.updateElement(oldBounds, e1);
        subject.updateElement(newBounds, e1);
        stub(timeService.nowMs()).toReturn(ElementBoundsAnimator.ANIMATION_DURATION_MS);

        subject.render(g, fm);

        verify(e1).draw(newBounds, g, fm);
        verifyNoMoreInteractions(e1);
    }

    @Test
    public void shouldAnimateAnUpdatedElement() {
        Rect oldBounds = Rect.fromTopLeft(1, 2, 10, 20);
        Rect newBounds = Rect.fromTopLeft(5, 6, 50, 60);
        subject.updateElement(oldBounds, e1);
        subject.updateElement(newBounds, e1);
        // no time passes yet

        subject.render(g, fm);

        verify(e1).draw(oldBounds, g, fm);
        verifyNoMoreInteractions(e1);
    }

    @Test
    public void shouldInterpolateAnUpdatedElement() {
        Rect oldBounds = Rect.fromTopLeft(1, 2, 10, 20);
        Rect newBounds = Rect.fromTopLeft(5, 6, 50, 60);
        subject.updateElement(oldBounds, e1);
        subject.updateElement(newBounds, e1);
        stub(timeService.nowMs()).toReturn(ElementBoundsAnimator.ANIMATION_DURATION_MS / 2);

        subject.render(g, fm);

        verify(e1).draw(Rect.fromTopLeft(3, 4, 30, 40), g, fm);
        verifyNoMoreInteractions(e1);
    }

    @Test
    public void shouldAnimateAnElementASecondTime() {
        Rect oldBounds = Rect.fromTopLeft(1, 2, 10, 20);
        Rect newBounds = Rect.fromTopLeft(5, 6, 50, 60);
        subject.updateElement(oldBounds, e1);
        subject.updateElement(newBounds, e1);
        stub(timeService.nowMs()).toReturn(ElementBoundsAnimator.ANIMATION_DURATION_MS);
        subject.updateElement(oldBounds, e1);
        stub(timeService.nowMs()).toReturn(2 * ElementBoundsAnimator.ANIMATION_DURATION_MS);

        subject.render(g, fm);

        verify(e1).draw(oldBounds, g, fm);
        verifyNoMoreInteractions(e1);
    }
}
