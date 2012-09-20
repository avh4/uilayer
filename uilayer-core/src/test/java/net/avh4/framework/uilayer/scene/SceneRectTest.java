package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import org.junit.Test;

public class SceneRectTest extends RenderTestBase {

    @Test
    public void testRenderRect() throws Exception {
        scene.add(new SceneRect(50, 50, 200, 500, Color.RED));
        scene.add(new SceneRect(450, 50, 200, 300, Color.YELLOW));
        assertRenderingOfSceneIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Rectangle: 50, 50, 200, 500, 0xffff0000\n" +
                "Rectangle: 450, 50, 200, 300, 0xffffff00\n");
    }
}
