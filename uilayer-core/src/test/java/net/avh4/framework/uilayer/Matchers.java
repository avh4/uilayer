package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class Matchers {

    static Matcher<SceneElement> placeholder(final String name, final int x,
                                             final int y, final int width, final int height) {
        return new TypeSafeMatcher<SceneElement>() {

            @Override
            public void describeTo(final Description arg0) {
            }

            @Override
            public boolean matchesSafely(final SceneElement item) {
                return item.equals(new ScenePlaceholder(name, x, y, width, height));
            }
        };
    }
}
