package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.math.Rect;
import org.junit.Test;

import java.util.ArrayList;

public class CompositeSceneElementTest extends RenderTestBase {
    private TestComponent subject;

    /**
     * This component draws a white square with a red boarder and blue circle.
     */
    private static class TestComponent extends CompositeSceneElement {

        public TestComponent(final int x, final int y, final int width,
                             final int height) {
            super(null, x, y, width, height);
        }

        @Override
        public Iterable<Item> getSceneElements(Rect bounds) {
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item(bounds, new SceneRect(0, 0, 0, 0, Color.RED)));
            items.add(new Item(bounds.inset(5), new SceneRect(0, 0, 0, 0, Color.WHITE)));
            items.add(new Item(bounds.inset(20), new SceneOval(0, 0, 0, 0, Color.BLUE)));
            return items;
        }
    }

    @Test
    public void testComponent() throws Exception {
        subject = new TestComponent(0, 0, 100, 100);
        assertRenderingOf(subject, "" +
                "=== TRANSLATE to 0, 0 ===\n" +
                "Rectangle: 0.0, 0.0, 100.0, 100.0, 0xffff0000\n" +
                "Rectangle: 5.0, 5.0, 90.0, 90.0, 0xffffffff\n" +
                "Oval: 20.0, 20.0, 60.0, 60.0, 0xff0000ff\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }

    @Test
    public void testTranslatedComponent() throws Exception {
        subject = new TestComponent(300, 400, 100, 100);
        assertRenderingOf(subject, "" +
                "=== TRANSLATE to 300, 400 ===\n" +
                "Rectangle: 0.0, 0.0, 100.0, 100.0, 0xffff0000\n" +
                "Rectangle: 5.0, 5.0, 90.0, 90.0, 0xffffffff\n" +
                "Oval: 20.0, 20.0, 60.0, 60.0, 0xff0000ff\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }
}
