package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import org.junit.Test;

public class SceneLineTest extends RenderTestBase {

    @Test
    public void testRenderLine() throws Exception {
        scene.add(new SceneLine(Color.WHITE, 100, 100, 200, 200));
        scene.add(new SceneLine(Color.YELLOW, 0, 300, 800, 0));
        assertRenderingIs(
                "Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                        "Line: 100, 100, 200, 200, 0xffffffff\n" +
                        "Line: 0, 300, 800, 0, 0xffffff00\n");
    }
}
