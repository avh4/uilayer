package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.SceneElementBase;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.Rect;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MatchersTest {

    @Test
    public void testPlaceholder() {
        final Matcher<SceneElement> matches = Matchers.placeholder("Item", 20,
                30, 40, 50);
        assertThat(new ScenePlaceholder("Item", 20, 30, 40, 50), matches);
        assertThat(new ScenePlaceholder("Item1", 20, 30, 40, 50), not(matches));
        assertThat(new ScenePlaceholder("Item", 21, 30, 40, 50), not(matches));
        assertThat(new ScenePlaceholder("Item", 20, 31, 40, 50), not(matches));
        assertThat(new ScenePlaceholder("Item", 20, 30, 41, 50), not(matches));
        assertThat(new ScenePlaceholder("Item", 20, 30, 40, 51), not(matches));
        // A custom subclass of SceneElement
        assertThat(new SceneElementBase("Item", 20, 30, 40, 50) {
            @Override
            public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
            }
        }, not(matches));
    }
}
