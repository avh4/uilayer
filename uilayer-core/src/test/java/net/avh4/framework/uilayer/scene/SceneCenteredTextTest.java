package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SceneCenteredTextTest extends RenderTestBase {

    @Test
    public void shouldHaveName() throws Exception {
        final SceneCenteredText subject = new SceneCenteredText("Text", 0, 0, 0, 0, null, 0);
        assertThat(subject.getName(), is("Text"));
    }

    @Test
    public void shouldHaveText() throws Exception {
        final SceneCenteredText subject = new SceneCenteredText("Apples", 0, 0, 0, 0, null, 0);
        assertThat(subject.getText(), is("Apples"));
    }

    @Test
    public void setText_shouldUpdateText() throws Exception {
        final SceneCenteredText subject = new SceneCenteredText("Apples", 0, 0, 0, 0, null, 0);
        subject.setText("Bananas");
        assertThat(subject.getText(), is("Bananas"));
    }

    @Test
    public void testRenderCenteredText() throws Exception {
        scene.add(new SceneRect(100, 50, 300, 150, Color.GREY));
        scene.add(new SceneCenteredText("CENTER", 100, 50, 300, 150, Font.PFENNIG.size(64), Color.WHITE));
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Rectangle: 100, 50, 300, 150, 0xff7f7f7f\n" +
                "Text: \"CENTER\" 134.5, 150.5 Font{'Pfennig.ttf' (64)} 0xffffffff\n");
    }

    @Test
    public void testBaselinesShouldBeAligned() throws Exception {
        scene.add(new SceneRect(100, 50, 75, 150, Color.darken(.5, Color.BLUE)));
        scene.add(new SceneCenteredText("A", 100, 50, 75, 150, Font.PFENNIG.size(64), Color.BLUE));
        scene.add(new SceneRect(175, 50, 75, 150, Color.darken(.5, Color.GREEN)));
        scene.add(new SceneCenteredText("a", 175, 50, 75, 150, Font.PFENNIG.size(64), Color.GREEN));
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Rectangle: 100, 50, 75, 150, 0xff00007f\n" +
                "Text: \"A\" 117.0, 150.5 Font{'Pfennig.ttf' (64)} 0xff0000ff\n" +
                "Rectangle: 175, 50, 75, 150, 0xff007f00\n" +
                "Text: \"a\" 197.5, 150.5 Font{'Pfennig.ttf' (64)} 0xff00ff00\n");
    }
}
