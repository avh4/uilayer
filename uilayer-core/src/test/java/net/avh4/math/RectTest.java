package net.avh4.math;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class RectTest {
    public static final int MIN_X = 10;
    public static final int MIN_Y = 20;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 40;
    Rect subject;

    @Before
    public void setUp() {
        subject = Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH, HEIGHT);
    }

    @Test
    public void testContainsPoint() {
        int CENTER_X = MIN_X + WIDTH / 2;
        int CENTER_Y = MIN_Y + HEIGHT / 2;
        // inside
        assertThat(subject.contains(CENTER_X, CENTER_Y), is(true));

        // edges
        assertThat(subject.contains(MIN_X, CENTER_Y), is(true));
        assertThat(subject.contains(MIN_X + WIDTH, CENTER_Y), is(true));
        assertThat(subject.contains(CENTER_X, MIN_Y), is(true));
        assertThat(subject.contains(CENTER_X, MIN_Y + HEIGHT), is(true));

        // outside
        assertThat(subject.contains(MIN_X - .0001, CENTER_Y), is(false));
        assertThat(subject.contains(MIN_X + WIDTH + .0001, CENTER_Y), is(false));
        assertThat(subject.contains(CENTER_X, MIN_Y - .0001), is(false));
        assertThat(subject.contains(CENTER_X, MIN_Y + HEIGHT + .0001), is(false));
    }

    @Test
    public void testContainsRect() {
        // inside
        assertThat(subject.contains(Rect.fromTopLeft(MIN_X + 1, MIN_Y + 1, WIDTH - 2, HEIGHT - 2)), is(true));

        // edges
        assertThat(subject.contains(Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH, HEIGHT)), is(true));

        // outside
        assertThat(subject.contains(Rect.fromTopLeft(MIN_X - 1, MIN_Y, WIDTH, HEIGHT)), is(false));
        assertThat(subject.contains(Rect.fromTopLeft(MIN_X, MIN_Y - 1, WIDTH, HEIGHT)), is(false));
        assertThat(subject.contains(Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH + 1, HEIGHT)), is(false));
        assertThat(subject.contains(Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH, HEIGHT + 1)), is(false));
    }

    @Test
    public void testDivide() {
        assertThat(subject.divide(0, 0, 1, 1), is(subject));
        assertThat(subject.divide(0, 0, .5, 1), is(Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH / 2, HEIGHT)));
        assertThat(subject.divide(0, 0, 1, .5), is(Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH, HEIGHT / 2)));
        assertThat(subject.divide(.5, 0, 1, 1), is(Rect.fromTopLeft(MIN_X + WIDTH / 2, MIN_Y, WIDTH / 2, HEIGHT)));
        assertThat(subject.divide(0, .5, 1, 1), is(Rect.fromTopLeft(MIN_X, MIN_Y + HEIGHT / 2, WIDTH, HEIGHT / 2)));

        assertThat(subject.divide(.2, .3, .4, .5),
                is(Rect.fromTopLeft(MIN_X + .2 * WIDTH, MIN_Y + .3 * HEIGHT, .2 * WIDTH, .2 * HEIGHT)));
    }

    @Test
    public void testInset() {
        assertThat(subject.inset(1, 2, 3, 4), is(Rect.fromTopLeft(MIN_X + 1, MIN_Y + 2, WIDTH - 1 - 3, HEIGHT - 2 - 4)));

        assertThat(subject.inset(1, 0, 0, 0), is(Rect.fromTopLeft(MIN_X + 1, MIN_Y, WIDTH - 1, HEIGHT)));
        assertThat(subject.inset(0, 2, 0, 0), is(Rect.fromTopLeft(MIN_X, MIN_Y + 2, WIDTH, HEIGHT - 2)));
        assertThat(subject.inset(0, 0, 3, 0), is(Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH - 3, HEIGHT)));
        assertThat(subject.inset(0, 0, 0, 4), is(Rect.fromTopLeft(MIN_X, MIN_Y, WIDTH, HEIGHT - 4)));
    }

    @Test
    public void testInset_withInsetsLargerThanOriginal() {
        Rect center = Rect.fromTopLeft(MIN_X + WIDTH / 2, MIN_Y + HEIGHT / 2, 0, 0);
        assertThat(subject.inset(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2), is(center));
        assertThat(subject.inset(WIDTH, HEIGHT, WIDTH, HEIGHT), is(center));
    }

    @Test
    public void testAspectRatio() {
        assertAspectRatio(subject, 4 / 3., subject.aspectRatio(4, 3));
        assertAspectRatio(subject, 9 / 16., subject.aspectRatio(9, 16));
    }

    @Test
    public void testScale() {
        assertThat(new Rect(0, 0, 10, 10).scale(new Rect(0, 0, 100, 100), new Rect(0, 0, 200, 200)),
                is(new Rect(0, 0, 20, 20)));
        assertThat(new Rect(10, 10, 10, 10).scale(new Rect(0, 0, 100, 100), new Rect(0, 0, 200, 200)),
                is(new Rect(20, 20, 20, 20)));
        assertThat(new Rect(10, 10, 10, 10).scale(new Rect(10, 10, 100, 100), new Rect(0, 0, 200, 200)),
                is(new Rect(0, 0, 20, 20)));
        assertThat(new Rect(10, 10, 10, 10).scale(new Rect(10, 10, 100, 100), new Rect(30, 30, 200, 200)),
                is(new Rect(30, 30, 20, 20)));
        assertThat(new Rect(10, 10, 10, 10).scale(new Rect(0, 0, 100, 100), new Rect(30, 30, 200, 200)),
                is(new Rect(50, 50, 20, 20)));
    }

    @Test
    public void testTranslate() {
        assertThat(new Rect(0, 0, 10, 10).translate(new Rect(0, 0, 100, 100), new Rect(0, 0, 200, 200)), is(new Rect(0, 0, 10, 10)));
        assertThat(new Rect(10, 10, 10, 10).translate(new Rect(0, 0, 100, 100), new Rect(5, 5, 200, 200)), is(new Rect(5, 5, 10, 10)));
        assertThat(new Rect(10, 10, 10, 10).translate(new Rect(5, 5, 100, 100), new Rect(0, 0, 200, 200)), is(new Rect(15, 15, 10, 10)));
    }

    @Test
    public void testInterpolate() {
        assertThat(Rect.interpolate(new Rect(0, 0, 0, 0), new Rect(10, 10, 10, 10), 0), is(new Rect(0, 0, 0, 0)));
        assertThat(Rect.interpolate(new Rect(0, 0, 0, 0), new Rect(10, 10, 10, 10), 1), is(new Rect(10, 10, 10, 10)));
        assertThat(Rect.interpolate(new Rect(1, 1, 1, 1), new Rect(10, 10, 10, 10), 0), is(new Rect(1, 1, 1, 1)));
        assertThat(Rect.interpolate(new Rect(2, 2, 2, 2), new Rect(10, 10, 10, 10), .5), is(new Rect(6, 6, 6, 6)));
    }

    @Test
    public void testResizeFromCenter() {
        assertThat(Rect.fromCenter(0, 0, 10, 10).resizeFromCenter(2, 2), is(Rect.fromCenter(0, 0, 2, 2)));
    }

    private void assertAspectRatio(Rect source, double ratio, Rect result) {
        assertThat(result.getWidth() / result.getHeight(), closeTo(ratio, 0.0000001));
        assertThat(result.getWidth(), lessThanOrEqualTo(source.getWidth()));
        assertThat(result.getHeight(), lessThanOrEqualTo(source.getHeight()));
        assertThat(result.getHeight() == source.getHeight() || result.getWidth() == source.getWidth(), is(true));
        assertThat(result.getMidX(), is(subject.getMidX()));
        assertThat(result.getMidY(), is(subject.getMidY()));
    }
}
