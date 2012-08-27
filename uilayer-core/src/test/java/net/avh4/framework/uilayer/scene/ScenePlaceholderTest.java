package net.avh4.framework.uilayer.scene;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScenePlaceholderTest extends RenderTestBase {

    @Test
    public void testEquals() {
        final ScenePlaceholder a = new ScenePlaceholder("Item", 10, 20, 30, 40);
        final ScenePlaceholder a2 = new ScenePlaceholder("Item", 10, 20, 30, 40);
        final ScenePlaceholder b = new ScenePlaceholder("Item 2", 10, 20, 30,
                40);
        final ScenePlaceholder c = new ScenePlaceholder("Item", 11, 20, 30, 40);
        final ScenePlaceholder d = new ScenePlaceholder("Item", 10, 21, 30, 40);
        final ScenePlaceholder e = new ScenePlaceholder("Item", 10, 20, 31, 40);
        final ScenePlaceholder f = new ScenePlaceholder("Item", 10, 20, 30, 41);
        assertTrue(a.equals(a2));
        assertTrue(a2.equals(a2));
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));
        assertFalse(a.equals(c));
        assertFalse(c.equals(a));
        assertFalse(a.equals(d));
        assertFalse(d.equals(a));
        assertFalse(a.equals(e));
        assertFalse(e.equals(a));
        assertFalse(a.equals(f));
        assertFalse(f.equals(a));
    }

    @Test
    public void testRenderPlaceholders() throws Exception {
        scene.add(new ScenePlaceholder("Body", 20, 20, 100, 560));
        assertRenderingOf(new ScenePlaceholder("Background", 0, 0, 800, 600), "" +
                "Rectangle: 0, 0, 800, 600, 0xffd1dea9\n" +
                "Text: \"Background\" 5.0, 595.0 Font{'Pfennig.ttf' (12)} 0xff000000\n");
    }

    @Test
    public void testRenderPlaceholders2() throws Exception {
        assertRenderingOf(new ScenePlaceholder("Body", 20, 20, 100, 560), "" +
                "Rectangle: 20, 20, 100, 560, 0xff1b10ac\n" +
                "Text: \"Body\" 25.0, 575.0 Font{'Pfennig.ttf' (12)} 0xffffffff\n");
    }
}
