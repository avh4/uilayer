package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.math.Rect;
import org.junit.Test;

public class SceneLabelTest extends RenderTestBase {

    @Test
    public void testRenderLabel() throws Exception {
        draw(new Rect(400, 300, 1, 1), new SceneRect(Color.RED));
        draw(new Rect(400, 300, 1, 1), new SceneLabel("Red Point", "Tuffy.ttf", 12, Color.YELLOW));
        assertRenderingIs("" +
                "Rectangle: 400.0, 300.0, 1.0, 1.0, 0xffff0000\n" +
                "Text: \"Red Point\" 376.5, 317.0 Font{'Tuffy.ttf' (12)} 0xffffff00\n");
    }
}
