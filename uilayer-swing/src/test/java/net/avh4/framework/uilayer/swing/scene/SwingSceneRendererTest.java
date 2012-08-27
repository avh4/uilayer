package net.avh4.framework.uilayer.swing.scene;

import net.avh4.framework.uilayer.scene.SceneElementBase;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.scene.SceneRenderer;
import net.avh4.framework.uilayer.scene.SwingGraphicsOperations;
import net.avh4.framework.uilayer.scene.SwingSceneRenderer;
import net.avh4.framework.uilayer.test.categories.FontRendering;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static net.avh4.util.imagecomparison.ImageComparisonMatchers.isApproved;
import static org.junit.Assert.assertThat;

public class SwingSceneRendererTest {

    private SwingGraphicsOperations graphicsOperations;

    @Before
    public void setup() throws Exception {
        graphicsOperations = new SwingGraphicsOperations();
    }

    @Test
    @Category(FontRendering.class)
    public void testRenderingASingleSceneElement() throws Exception {
        final SceneElementBase e = new ScenePlaceholder("Element", 0, 0, 100, 100);
        final SwingSceneRenderer subject = new SwingSceneRenderer(graphicsOperations, new SceneRenderer(e));
        assertThat(subject, isApproved());
    }
}
