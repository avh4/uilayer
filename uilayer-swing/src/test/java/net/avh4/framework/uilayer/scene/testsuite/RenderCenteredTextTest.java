package net.avh4.framework.uilayer.scene.testsuite;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.scene.SceneCenteredText;
import net.avh4.framework.uilayer.scene.SceneRect;
import net.avh4.framework.uilayer.test.categories.FontRendering;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FontRendering.class)
public class RenderCenteredTextTest extends RenderTestBase {

    @Test
    public void testRenderCenteredText() throws Exception {
        scene.add(new SceneRect(100, 50, 300, 150, Color.GREY));
        scene.add(new SceneCenteredText("CENTER", 100, 50, 300, 150, Font.PFENNIG.size(64), Color.WHITE));
        assertRenderingIsApproved();
    }

    @Test
    public void testBaselinesShouldBeAligned() throws Exception {
        scene.add(new SceneRect(100, 50, 75, 150, Color.darken(.5, Color.BLUE)));
        scene.add(new SceneCenteredText("A", 100, 50, 75, 150, Font.PFENNIG.size(64), Color.BLUE));
        scene.add(new SceneRect(175, 50, 75, 150, Color.darken(.5, Color.GREEN)));
        scene.add(new SceneCenteredText("a", 175, 50, 75, 150, Font.PFENNIG.size(64), Color.GREEN));
        assertRenderingIsApproved();
    }
}
