package net.avh4.framework.uilayer.swing.scene;

import net.avh4.framework.uilayer.swing.scene.SwingPlaceholder;
import net.avh4.framework.uilayer.swing.scene.SwingSceneObject;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Matchers {

	static Matcher<SwingSceneObject> placeholder(final String name, final int x,
			final int y, final int width, final int height) {
		return new TypeSafeMatcher<SwingSceneObject>() {

			@Override
			public void describeTo(final Description arg0) {

			}

			@Override
			public boolean matchesSafely(final SwingSceneObject item) {
				return item.equals(new SwingPlaceholder(name, x, y, width, height));
			}
		};
	}

}
