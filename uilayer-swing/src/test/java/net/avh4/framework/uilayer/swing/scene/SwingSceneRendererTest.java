package net.avh4.framework.uilayer.swing.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.swing.SwingGraphicsOperations;
import net.avh4.framework.uilayer.swing.SwingSceneRenderer;
import net.avh4.framework.uilayer.test.annotations.RequiresPreciseFontRendering;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;

import static net.avh4.util.imagecomparison.hamcrest.ImageComparisonMatchers.isApproved;
import static org.junit.Assert.assertThat;

public class SwingSceneRendererTest {

    private SwingGraphicsOperations graphicsOperations;

    @Rule
    public MethodRule r = new RequiresPreciseFontRendering.Rule();

    @BeforeClass
    public static void ensureAntialiasingIsOff() {
        SwingGraphicsOperations.USE_ANTIALIASING = false;
    }

    @Before
    public void setup() throws Exception {
        graphicsOperations = new SwingGraphicsOperations();
    }

    @Test
    @RequiresPreciseFontRendering("1.7.0-u8-b04")
    public void testRenderingASingleSceneElement() throws Exception {
        final Element e = new ScenePlaceholder("Element");
        final SwingSceneRenderer subject = new SwingSceneRenderer(graphicsOperations, e);
        assertThat(subject, isApproved());
    }
}
