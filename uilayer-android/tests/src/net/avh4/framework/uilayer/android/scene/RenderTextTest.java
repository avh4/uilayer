package net.avh4.framework.uilayer.android.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.framework.uilayer.scene.SceneText;
import net.avh4.framework.uilayer.swing.scene.Font;

public class RenderTextTest extends RenderTestBase {

	public void testRenderText() throws Exception {
		final String longString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		scene.add(new ScenePlaceholder("Text box: size 36", 50, 50, 200, 1000));
		scene.add(new SceneText(longString, 50, 50, 200, "Tuffy.ttf", 36, Color.WHITE));
		scene.add(new ScenePlaceholder("Text box: size 12", 450, 50, 200, 1000));
		scene.add(new SceneText(longString, 450, 50, 200, "Tuffy.ttf", 12, Color.WHITE));
		assertRenderingIsApproved();
	}

	public void testUpdateText() throws Exception {
		final SceneText text = scene.add(new SceneText("Wrong", 10, 10, 100, "Tuffy.ttf", 36, Color.WHITE));
		text.setText("Correct");
        assertRenderingIsApproved();
	}

	public void testColoredText() throws Exception {
		scene.add(new SceneText("Blue", 10, 10, 100, "Tuffy.ttf", 36, Color.BLUE));
        assertRenderingIsApproved();
	}

	public void ignore_testIncludedFontPfennig() throws Exception {
        // TODO: loading typefaces see AndroidSceneRenderer.loadFont
		scene.add(new SceneText("Pfennig", 0, 0, 300, Font.PFENNIG, 32, Color.YELLOW));
        assertRenderingIsApproved();
	}

}
