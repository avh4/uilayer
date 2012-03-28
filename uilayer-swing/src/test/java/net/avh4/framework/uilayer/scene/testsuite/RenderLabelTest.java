package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.scene.SceneLabel;
import net.avh4.framework.uilayer.scene.SceneRect;

public class RenderLabelTest extends RenderTestBase {

    public void testRenderLabel() throws Exception {
        scene.add(new SceneRect(400, 300, 1, 1, Color.RED));
        scene.add(new SceneLabel("Red Point", 400, 300, "Tuffy.ttf", 12,
                Color.YELLOW));
        assertRenderingIsApproved();
    }

}
