package net.avh4.framework.uilayer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

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
	public void testGetValue() {
		assertThat(Color.getValue(0xff000000), is(0x00));
		assertThat(Color.getValue(0xff7f7f7f), is(0x7f));
		assertThat(Color.getValue(0xffffffff), is(0xff));

		assertThat(Color.getValue(0xff83f234), is(0xf2));
	}
}