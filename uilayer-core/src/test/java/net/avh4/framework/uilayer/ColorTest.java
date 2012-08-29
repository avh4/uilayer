package net.avh4.framework.uilayer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("NestedMethodCall")
public class ColorTest {

    @Test
    public void testGetRed() {
        assertThat(Color.getRed(0xff3f7493), is(0x3f));
    }

    @Test
    public void testGetGreen() {
        assertThat(Color.getGreen(0xff3f7493), is(0x74));
    }

    @Test
    public void testGetBlue() {
        assertThat(Color.getBlue(0xff3f7493), is(0x93));
    }

    @Test
    public void testGetAlpha() throws Exception {
        assertThat(Color.getAlpha(0x73020340), is(0x73));
    }

    @Test
    public void testGetValue() {
        assertThat(Color.getValue(0xff000000), is(0x00));
        assertThat(Color.getValue(0xff7f7f7f), is(0x7f));
        assertThat(Color.getValue(0xffffffff), is(0xff));

        assertThat(Color.getValue(0xff83f234), is(0xf2));
    }

    @Test
    public void testDarken() {
        assertThat(Color.darken(0.1, 0xff0a0a0a), is(0xff090909));
        assertThat(Color.darken(0.1, 0xff646464), is(0xff5a5a5a));
    }

    @Test
    public void testFromRGB() throws Exception {
        assertThat(Color.fromRGB(0x01, 0x02, 0x03), is(0xff010203));
        assertThat(Color.fromRGB(0xfe, 0xfd, 0xfc), is(0xfffefdfc));
        assertThat(Color.fromRGB(0x40, 0xd4, 0x09), is(0xff40d409));
    }
}
