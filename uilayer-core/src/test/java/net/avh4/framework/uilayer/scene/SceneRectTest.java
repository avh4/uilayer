package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import org.junit.Test;

public class SceneRectTest extends RenderTestBase {

    @Test
    public void testRenderRect() throws Exception {
        scene.add(new SceneRect(50, 50, 200, 500, Color.RED));
        scene.add(new SceneRect(450, 50, 200, 300, Color.YELLOW));
        assertRenderingOfSceneIs("Rectangle: 0.0, 0.0, 800.0, 600.0, 0xff000000\n" +
                "Rectangle: 50.0, 50.0, 200.0, 500.0, 0xffff0000\n" +
                "Rectangle: 450.0, 50.0, 200.0, 300.0, 0xffffff00\n");
    }
}
