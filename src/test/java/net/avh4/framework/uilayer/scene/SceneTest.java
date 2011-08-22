package net.avh4.framework.uilayer.scene;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import net.avh4.framework.uilayer.scene.Scene;

import org.junit.Test;

public class SceneTest {

	@Test
	public void testDefaultTitle() {
		final Scene subject = new Scene();
		assertThat(subject.getTitle(), is("Untitled Scene"));
	}

	@Test
	public void testTitle() {
		final Scene subject = new Scene("My Title");
		assertThat(subject.getTitle(), is("My Title"));
	}

}
