package net.avh4.framework.uilayer.scene;


import net.avh4.framework.uilayer.Color;

public class RenderLabelTest extends RenderTestBase {

    public void testRenderLabel() throws Exception {
        scene.add(new SceneRect(400, 300, 1, 1, Color.RED));
        scene.add(new SceneLabel("Red Point", 400, 300, "Tuffy.ttf", 12,
                Color.YELLOW));
        assertRenderingIsApproved();
    }
}
