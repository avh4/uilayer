package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.scene.SceneText;
import net.avh4.framework.uilayer.test.categories.FontRendering;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RenderPlaceholdersTest extends RenderTestBase {

    @Test
    @Category(FontRendering.class)
    public void testRenderPlaceholders() throws Exception {
        scene.add(new ScenePlaceholder("Background", 0, 0, 800, 600));
        scene.add(new ScenePlaceholder("Body", 20, 20, 100, 560));
        assertRenderingIsApproved();
    }

    @Test
    @Category(FontRendering.class)
    public void testCorrectFontWithTextInTheScene() throws Exception {
        scene.add(new SceneText("Text", 200, 200, 100, "Tuffy.ttf", 24, Color.WHITE));
        scene.add(new ScenePlaceholder("Box", 200, 200, 100, 100));
        assertRenderingIsApproved();
    }

    @Test
    public void testHiddenElement() throws Exception {
        final ScenePlaceholder hidden = new ScenePlaceholder("Hidden", 100, 100, 200, 200);
        hidden.setHidden(true);
        scene.add(hidden);
        assertRenderingIsApproved();
    }
}
