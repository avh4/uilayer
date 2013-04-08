package net.avh4.framework.uilayer.scene;

import net.avh4.math.Rect;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScenePlaceholderTest extends RenderTestBase {

    @Test
    public void testEquals() {
        final ScenePlaceholder a = new ScenePlaceholder("Item");
        final ScenePlaceholder a2 = new ScenePlaceholder("Item");
        final ScenePlaceholder b = new ScenePlaceholder("Item 2");
        assertTrue(a.equals(a2));
        assertTrue(a2.equals(a2));
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
    }

    @Test
    public void testRenderPlaceholders() throws Exception {
        draw(Rect.fromTopLeft(0, 0, 800, 600), new ScenePlaceholder("Background"));
        assertRenderingIs("" +
                "Rectangle: 0.0, 0.0, 800.0, 600.0, 0xffd1dea9\n" +
                "Text: \"Background\" 5.0, 595.0 Font{'Pfennig.ttf' (12)} 0xff000000\n");
    }

    @Test
    public void testRenderPlaceholders2() throws Exception {
        draw(Rect.fromTopLeft(20, 20, 100, 560), new ScenePlaceholder("Body"));
        assertRenderingIs("" +
                "Rectangle: 20.0, 20.0, 100.0, 560.0, 0xff1b10ac\n" +
                "Text: \"Body\" 25.0, 575.0 Font{'Pfennig.ttf' (12)} 0xffffffff\n");
    }
}
