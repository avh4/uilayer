package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.math.Rect;
import org.junit.Test;

public class SceneRectTest extends RenderTestBase {

    @Test
    public void testRenderRect() throws Exception {
        draw(new Rect(50, 50, 200, 500), new SceneRect(Color.RED));
        draw(new Rect(450, 50, 200, 300), new SceneRect(Color.YELLOW));
        assertRenderingIs("" +
                "Rectangle: 50.0, 50.0, 200.0, 500.0, 0xffff0000\n" +
                "Rectangle: 450.0, 50.0, 200.0, 300.0, 0xffffff00\n");
    }
}
