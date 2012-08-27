package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import org.junit.Test;

public class SceneLabelTest extends RenderTestBase {

    @Test
    public void testRenderLabel() throws Exception {
        scene.add(new SceneRect(400, 300, 1, 1, Color.RED));
        scene.add(new SceneLabel("Red Point", 400, 300, "Tuffy.ttf", 12, Color.YELLOW));
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Rectangle: 400, 300, 1, 1, 0xffff0000\n" +
                "Text: \"Red Point\" 376.5, 317.0 Font{'Tuffy.ttf' (12)} 0xffffff00\n");
    }
}
