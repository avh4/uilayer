package net.avh4.framework.uilayer.scene;

import net.avh4.math.Rect;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HideableElementTest {

    private HideableElement subject;
    private boolean didDraw = false;

    private class TestElement extends HideableElement {

        public TestElement(final String name, final int x, final int y,
                           final int width, final int height) {
            super(name, x, y, width, height);
        }

        @Override
        public void drawGivenVisible(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
            didDraw = true;
        }
    }

    @Before
    public void setUp() {
        subject = new TestElement("name", 10, 20, 100, 200);
    }

    @Test
    public void testDrawsWhenNotHidden() throws Exception {
        subject.draw(null, null, null);
        assertThat(didDraw, is(true));
    }

    @Test
    public void testDoesntDrawWhenHidden() throws Exception {
        subject.setHidden(true);
        subject.draw(null, null, null);
        assertThat(didDraw, is(false));
    }
}
