package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.math.Rect;
import org.junit.Test;

public class SceneOvalTest extends RenderTestBase {

    @Test
    public void testRenderOval() throws Exception {
        draw(Rect.fromTopLeft(50, 50, 200, 500), new SceneOval(Color.RED));
        draw(Rect.fromTopLeft(450, 50, 200, 300), new SceneOval(Color.YELLOW));
        assertRenderingIs("" +
                "Oval: 50.0, 50.0, 200.0, 500.0, 0xffff0000\n" +
                "Oval: 450.0, 50.0, 200.0, 300.0, 0xffffff00\n");
    }
}
