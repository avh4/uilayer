package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.math.Rect;
import org.junit.Test;

public class SceneLineTest extends RenderTestBase {

    @Test
    public void testRenderLine() throws Exception {
        draw(new Rect(0, 0, 0, 0), new SceneLine(Color.WHITE, 100, 100, 200, 200));
        draw(new Rect(0, 0, 0, 0), new SceneLine(Color.YELLOW, 0, 300, 800, 0));
        assertRenderingIs("" +
                "Line: 100, 100, 200, 200, 0xffffffff\n" +
                "Line: 0, 300, 800, 0, 0xffffff00\n");
    }
}
