package net.avh4.framework.uilayer.scene;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class SceneElementBaseTest {

    private SceneElementBase subject;

    private static class TestElement extends SceneElementBase {

        public TestElement(final String name, final int x, final int y,
                           final int width, final int height) {
            super(name, x, y, width, height);
        }

        @Override
        public void draw(GraphicsOperations g, FontMetricsService fm) {
        }
    }

    @Before
    public void setUp() {
        subject = new TestElement("name", 10, 20, 100, 200);
    }

    @Test
    public void testChangeDimensions() {
        final double NEW_WIDTH = 199;
        final double NEW_HEIGHT = 299;
        assertThat(subject.getWidth(), not(NEW_WIDTH));
        assertThat(subject.getHeight(), not(NEW_HEIGHT));

        subject.setWidth(NEW_WIDTH);
        subject.setHeight(NEW_HEIGHT);

        assertThat(subject.getWidth(), is(NEW_WIDTH));
        assertThat(subject.getHeight(), is(NEW_HEIGHT));
    }

    @Test
    public void testChangePosition() {
        final double NEW_X = 19;
        final double NEW_Y = 29;
        assertThat(subject.getX(), not(NEW_X));
        assertThat(subject.getY(), not(NEW_Y));

        subject.setX(NEW_X);
        subject.setY(NEW_Y);

        assertThat(subject.getX(), is(NEW_X));
        assertThat(subject.getY(), is(NEW_Y));
    }

    @Test
    public void testIsHidden() throws Exception {
        assertThat(subject.isHidden(), is(false));
    }

    @Test
    public void testSetHidden() throws Exception {
        subject.setHidden(true);
        assertThat(subject.isHidden(), is(true));
    }
}
