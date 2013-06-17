package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.math.geometry.Rect;
import org.junit.Test;

public class SceneLineTest extends RenderTestBase {

    @Test
    public void testRenderLine() throws Exception {
        draw(Rect.fromTopLeft(0, 0, 0, 0), new SceneLine(Color.WHITE, 100, 100, 200, 200));
        draw(Rect.fromTopLeft(0, 0, 0, 0), new SceneLine(Color.YELLOW, 0, 300, 800, 0));
        assertRenderingIs("" +
                "Line: 100.0, 100.0, 200.0, 200.0, 0xffffffff\n" +
                "Line: 0.0, 300.0, 800.0, 0.0, 0xffffff00\n");
    }
}
