package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import org.junit.Test;

public class CompositeSceneElementTest extends RenderTestBase {
    private TestComponent subject;

    /**
     * This component draws a white square with a red boarder and blue circle.
     */
    private static class TestComponent extends CompositeSceneElement {

        private final Scene scene;

        public TestComponent(final int x, final int y, final int width,
                             final int height) {
            super(null, x, y, width, height);
            scene = new Scene();
            scene.add(new SceneRect(0, 0, 100, 100, Color.RED));
            scene.add(new SceneRect(5, 5, 90, 90, Color.WHITE));
            scene.add(new SceneOval(20, 20, 60, 60, Color.BLUE));
        }

        @Override
        public Iterable<SceneElement> getSceneElements() {
            return scene;
        }
    }

    @Test
    public void testComponent() throws Exception {
        subject = new TestComponent(0, 0, 100, 100);
        assertRenderingOf(subject, "" +
                "=== TRANSLATE to 0, 0 ===\n" +
                "Rectangle: 0.0, 0.0, 100.0, 100.0, 0xffff0000\n" +
                "Rectangle: 5.0, 5.0, 90.0, 90.0, 0xffffffff\n" +
                "Oval: 20, 20, 60, 60, 0xff0000ff\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }

    @Test
    public void testTranslatedComponent() throws Exception {
        subject = new TestComponent(300, 400, 100, 100);
        assertRenderingOf(subject, "" +
                "=== TRANSLATE to 300, 400 ===\n" +
                "Rectangle: 0.0, 0.0, 100.0, 100.0, 0xffff0000\n" +
                "Rectangle: 5.0, 5.0, 90.0, 90.0, 0xffffffff\n" +
                "Oval: 20, 20, 60, 60, 0xff0000ff\n" +
                "=== TRANSLATE to 0, 0 ===\n");
    }
}
