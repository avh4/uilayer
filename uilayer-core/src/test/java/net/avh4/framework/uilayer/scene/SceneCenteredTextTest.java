package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.math.geometry.Rect;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SceneCenteredTextTest extends RenderTestBase {

    @Test
    public void shouldHaveText() throws Exception {
        final SceneCenteredText subject = new SceneCenteredText("Apples", null, 0);
        assertThat(subject.getText(), is("Apples"));
    }

    @Test
    public void setText_shouldUpdateText() throws Exception {
        final SceneCenteredText subject = new SceneCenteredText("Apples", null, 0);
        subject.setText("Bananas");
        assertThat(subject.getText(), is("Bananas"));
    }

    @Test
    public void testRenderCenteredText() throws Exception {
        Rect bounds = Rect.fromTopLeft(100, 50, 300, 150);
        draw(bounds, new SceneRect(Color.GREY));
        draw(bounds, new SceneCenteredText("CENTER", Font.PFENNIG.size(64), Color.WHITE));
        assertRenderingIs("" +
                "Rectangle: 100.0, 50.0, 300.0, 150.0, 0xff7f7f7f\n" +
                "Text: \"CENTER\" 134.5, 150.5 Font{'Pfennig.ttf' (64)} 0xffffffff\n");
    }

    @Test
    public void testBaselinesShouldBeAligned() throws Exception {
        draw(Rect.fromTopLeft(100, 50, 75, 150), new SceneRect(Color.darken(.5, Color.BLUE)));
        draw(Rect.fromTopLeft(100, 50, 75, 150), new SceneCenteredText("A", Font.PFENNIG.size(64), Color.BLUE));
        draw(Rect.fromTopLeft(175, 50, 75, 150), new SceneRect(Color.darken(.5, Color.GREEN)));
        draw(Rect.fromTopLeft(175, 50, 75, 150), new SceneCenteredText("a", Font.PFENNIG.size(64), Color.GREEN));
        assertRenderingIs("" +
                "Rectangle: 100.0, 50.0, 75.0, 150.0, 0xff00007f\n" +
                "Text: \"A\" 117.0, 150.5 Font{'Pfennig.ttf' (64)} 0xff0000ff\n" +
                "Rectangle: 175.0, 50.0, 75.0, 150.0, 0xff007f00\n" +
                "Text: \"a\" 197.5, 150.5 Font{'Pfennig.ttf' (64)} 0xff00ff00\n");
    }
}
