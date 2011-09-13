package net.avh4.framework.uilayer.swing.scene;

import java.awt.Graphics;

import net.avh4.framework.uilayer.scene.SceneObject;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Matchers {

	static Matcher<SceneObject<Graphics>> placeholder(final String name,
			final int x, final int y, final int width, final int height) {
		return new TypeSafeMatcher<SceneObject<Graphics>>() {

			@Override
			public void describeTo(final Description arg0) {

			}

			@Override
			public boolean matchesSafely(final SceneObject<Graphics> item) {
				return item.equals(new SwingPlaceholder(name, x, y, width,
						height));
			}
		};
	}

}
