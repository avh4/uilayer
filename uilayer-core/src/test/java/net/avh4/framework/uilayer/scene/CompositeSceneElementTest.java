package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.math.Rect;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CompositeSceneElementTest extends RenderTestBase {
    private TestComponent subject;

    /**
     * This component draws a white square with a red boarder and blue circle.
     */
    private static class TestComponent extends CompositeSceneElement {
        @Override
        public Iterable<Item> getSceneElements(Rect bounds) {
            ArrayList<Item> items = new ArrayList<Item>();
            items.add(new Item(bounds, new SceneRect(Color.RED)));
            items.add(new Item(bounds.inset(5), new SceneRect(Color.WHITE)));
            items.add(new Item(bounds.inset(20), new SceneOval(0, 0, 0, 0, Color.BLUE)));
            return items;
        }
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        subject = new TestComponent();
    }

    @Test
    public void testComponent() throws Exception {
        assertRenderingOf(new Rect(0, 0, 100, 100),
                subject, "" +
                "=== TRANSLATE to 0, 0 ===\n" +
                "Rectangle: 0.0, 0.0, 100.0, 100.0, 0xffff0000\n" +
                "Rectangle: 5.0, 5.0, 90.0, 90.0, 0xffffffff\n" +
                "Oval: 20.0, 20.0, 60.0, 60.0, 0xff0000ff\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }

    @Test
    public void testTranslatedComponent() throws Exception {
        assertRenderingOf(new Rect(300, 400, 100, 100),
                subject, "" +
                "=== TRANSLATE to 300, 400 ===\n" +
                "Rectangle: 0.0, 0.0, 100.0, 100.0, 0xffff0000\n" +
                "Rectangle: 5.0, 5.0, 90.0, 90.0, 0xffffffff\n" +
                "Oval: 20.0, 20.0, 60.0, 60.0, 0xff0000ff\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }
}
