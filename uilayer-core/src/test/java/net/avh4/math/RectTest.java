package net.avh4.math;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RectTest {
    public static final int MIN_X = 10;
    public static final int MIN_Y = 20;
    public static final int WIDTH = 30;
    public static final int HEIGHT = 40;
    Rect subject;

    @Before
    public void setUp() {
        subject = new Rect(MIN_X, MIN_Y, WIDTH, HEIGHT);
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
        assertThat(subject.contains(new Rect(MIN_X + 1, MIN_Y + 1, WIDTH - 2, HEIGHT - 2)), is(true));

        // edges
        assertThat(subject.contains(new Rect(MIN_X, MIN_Y, WIDTH, HEIGHT)), is(true));

        // outside
        assertThat(subject.contains(new Rect(MIN_X - 1, MIN_Y, WIDTH, HEIGHT)), is(false));
        assertThat(subject.contains(new Rect(MIN_X, MIN_Y - 1, WIDTH, HEIGHT)), is(false));
        assertThat(subject.contains(new Rect(MIN_X, MIN_Y, WIDTH + 1, HEIGHT)), is(false));
        assertThat(subject.contains(new Rect(MIN_X, MIN_Y, WIDTH, HEIGHT + 1)), is(false));
    }

    @Test
    public void testDivide() {
        assertThat(subject.divide(0, 0, 1, 1), is(subject));
        assertThat(subject.divide(0, 0, .5, 1), is(new Rect(MIN_X, MIN_Y, WIDTH / 2, HEIGHT)));
        assertThat(subject.divide(0, 0, 1, .5), is(new Rect(MIN_X, MIN_Y, WIDTH, HEIGHT / 2)));
        assertThat(subject.divide(.5, 0, 1, 1), is(new Rect(MIN_X + WIDTH / 2, MIN_Y, WIDTH / 2, HEIGHT)));
        assertThat(subject.divide(0, .5, 1, 1), is(new Rect(MIN_X, MIN_Y + HEIGHT / 2, WIDTH, HEIGHT / 2)));

        assertThat(subject.divide(.2, .3, .4, .5),
                is(new Rect(MIN_X + .2 * WIDTH, MIN_Y + .3 * HEIGHT, .2 * WIDTH, .2 * HEIGHT)));
    }

    @Test
    public void testInset() {
        assertThat(subject.inset(1, 2, 3, 4), is(new Rect(MIN_X + 1, MIN_Y + 2, WIDTH - 1 - 3, HEIGHT - 2 - 4)));

        assertThat(subject.inset(1, 0, 0, 0), is(new Rect(MIN_X + 1, MIN_Y, WIDTH - 1, HEIGHT)));
        assertThat(subject.inset(0, 2, 0, 0), is(new Rect(MIN_X, MIN_Y + 2, WIDTH, HEIGHT - 2)));
        assertThat(subject.inset(0, 0, 3, 0), is(new Rect(MIN_X, MIN_Y, WIDTH - 3, HEIGHT)));
        assertThat(subject.inset(0, 0, 0, 4), is(new Rect(MIN_X, MIN_Y, WIDTH, HEIGHT - 4)));
    }

    @Test
    public void testInset_withInsetsLargerThanOriginal() {
        Rect center = new Rect(MIN_X + WIDTH / 2, MIN_Y + HEIGHT / 2, 0, 0);
        assertThat(subject.inset(WIDTH / 2, HEIGHT / 2, WIDTH / 2, HEIGHT / 2), is(center));
        assertThat(subject.inset(WIDTH, HEIGHT, WIDTH, HEIGHT), is(center));
    }
}
