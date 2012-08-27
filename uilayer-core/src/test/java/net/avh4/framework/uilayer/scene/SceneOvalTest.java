package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import org.junit.Test;

public class SceneOvalTest extends RenderTestBase {

    @Test
    public void testRenderOval() throws Exception {
        scene.add(new SceneOval(50, 50, 200, 500, Color.RED));
        scene.add(new SceneOval(450, 50, 200, 300, Color.YELLOW));
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Oval: 50, 50, 200, 500, 0xffff0000\n" +
                "Oval: 450, 50, 200, 300, 0xffffff00\n");
    }
}