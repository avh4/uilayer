package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.scene.SceneText;

public class RenderPlaceholdersTest extends RenderTestBase {

    public void testRenderPlaceholders() throws Exception {
        scene.add(new ScenePlaceholder("Background", 0, 0, 800, 600));
        scene.add(new ScenePlaceholder("Body", 20, 20, 100, 560));
        assertRenderingIsApproved();
    }

    public void testCorrectFontWithTextInTheScene() throws Exception {
        scene.add(new SceneText("Text", 200, 200, 100, "Tuffy.ttf", 24, Color.WHITE));
        scene.add(new ScenePlaceholder("Box", 200, 200, 100, 100));
        assertRenderingIsApproved();
    }

    public void testHiddenElement() throws Exception {
        final ScenePlaceholder hidden = new ScenePlaceholder("Hidden", 100, 100, 200, 200);
        hidden.setVisible(false);
        scene.add(hidden);
        assertRenderingIsApproved();
    }
}
