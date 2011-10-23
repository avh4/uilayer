package net.avh4.framework.uilayer.swing.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.scene.SceneText;

import org.junit.Test;

public class RenderPlaceholdersTest extends RenderTestBase {

	@Test
	public void testRenderPlaceholders() throws Exception {
		scene.add(new ScenePlaceholder("Background", 0, 0, 800, 600));
		scene.add(new ScenePlaceholder("Body", 20, 20, 100, 560));
		assertThat(subject, isApproved());
	}

	@Test
	public void testCorrectFontWithTextInTheScene() throws Exception {
		scene.add(new SceneText("Text", 0, 0, 100, "Tuffy.ttf", 50, Color.WHITE));
		scene.add(new ScenePlaceholder("Box", 200, 200, 100, 100));
		assertThat(subject, isApproved());
	}

}
