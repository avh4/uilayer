package net.avh4.framework.uilayer;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
}
