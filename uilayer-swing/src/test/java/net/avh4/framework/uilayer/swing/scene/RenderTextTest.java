package net.avh4.framework.uilayer.swing.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;
import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.scene.SceneText;

import org.junit.Test;

public class RenderTextTest extends RenderTestBase {

	@Test
	public void testRenderText() throws Exception {
		final String longString = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		scene.addPlaceholder("Text box: size 36", 50, 50, 200, 1000);
		scene.addText(longString, 50, 50, 200, "Tuffy.ttf", 36, Color.WHITE);
		scene.addPlaceholder("Text box: size 12", 450, 50, 200, 1000);
		scene.addText(longString, 450, 50, 200, "Tuffy.ttf", 12, Color.WHITE);
		assertThat(subject, isApproved());
	}

	@Test
	public void testUpdateText() throws Exception {
		final SceneText text = scene.addText("Wrong", 10, 10, 100, "Tuffy.ttf",
				36, Color.WHITE);
		text.setText("Correct");

		assertThat(subject, isApproved());
	}

	@Test
	public void testColoredText() throws Exception {
		scene.addText("Blue", 10, 10, 100, "Tuffy.ttf", 36, Color.BLUE);

		assertThat(subject, isApproved());
	}

	@Test
	public void testIncludedFontPfennig() throws Exception {
		scene.addText("Pfennig", 0, 0, 300, Font.PFENNIG, 32, Color.YELLOW);

		assertThat(subject, isApproved());
	}

}
