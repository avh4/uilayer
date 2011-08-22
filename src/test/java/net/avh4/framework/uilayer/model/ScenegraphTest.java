package net.avh4.framework.uilayer.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ScenegraphTest {

	@Test
	public void testDefaultTitle() {
		final Scenegraph subject = new Scenegraph();
		assertThat(subject.getTitle(), is("Untitled Scene"));
	}

	@Test
	public void testTitle() {
		final Scenegraph subject = new Scenegraph("My Title");
		assertThat(subject.getTitle(), is("My Title"));
	}

}
