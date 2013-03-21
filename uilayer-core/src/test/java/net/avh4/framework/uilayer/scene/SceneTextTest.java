package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.math.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;

public class SceneTextTest extends RenderTestBase {
    private SceneText subjectWithTextAsName;
    private SceneText subject;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Font.service = Mockito.mock(UILayerService.class);
        Mockito.stub(Font.service.getFontHeight(Font.PFENNIG)).toReturn(137);
        Mockito.stub(fm.getAscent(Font.PFENNIG)).toReturn(13.0f);
        Mockito.stub(fm.getLineHeight(Font.PFENNIG)).toReturn(17.0f);
        Mockito.stub(fm.stringWidth(Font.PFENNIG, "Word")).toReturn(50f);
        Mockito.stub(fm.stringWidth(Font.PFENNIG, "Word ")).toReturn(55f);

        subject = new SceneText("Text to display", Font.PFENNIG, 0);
        subjectWithTextAsName = new SceneText("Text to display", Font.PFENNIG, 0);
    }

    @Test
    public void getText_shouldReturnText() throws Exception {
        assertThat(subjectWithTextAsName.getText(), is("Text to display"));
    }

    @Test
    public void getText_afterSetText_shouldReturnUpdateText() throws Exception {
        subjectWithTextAsName.setText("Updated text");
        assertThat(subjectWithTextAsName.getText(), is("Updated text"));
    }

    @Test
    public void shouldHaveText() throws Exception {
        assertThat(subject.getText(), is("Text to display"));
    }

    @Test
    public void shouldUpdateText() throws Exception {
        subject.setText("Correct");
        assertThat(subject.getText(), is("Correct"));
    }

    @Test
    public void shouldDrawTheTextWithTheCorrectBaselinePosition() throws Exception {
        Mockito.stub(fm.getAscent(Font.PFENNIG)).toReturn(13.0f);
        draw(new Rect(0, 0, 1000, 0), new SceneText("Word", Font.PFENNIG, Color.WHITE));
        assertRenderingIs("Text: \"Word\" 0.0, 13.0 Font{'Pfennig.ttf' (12)} 0xffffffff\n");
    }

    @Test
    public void shouldDrawColoredText() throws Exception {
        Mockito.stub(fm.getAscent(Font.PFENNIG)).toReturn(13.0f);
        draw(new Rect(0, 0, 1000, 0), new SceneText("Word", Font.PFENNIG, Color.BLUE));
        assertRenderingIs("Text: \"Word\" 0.0, 13.0 Font{'Pfennig.ttf' (12)} 0xff0000ff\n");
    }

    @Test
    public void shouldWrapLines() throws Exception {
        draw(new Rect(0, 0, 50, 0), new SceneText("Word Word", Font.PFENNIG, Color.WHITE));
        assertRenderingIs("" +
                "Text: \"Word\" 0.0, 13.0 Font{'Pfennig.ttf' (12)} 0xffffffff\n" +
                "Text: \"Word\" 0.0, 30.0 Font{'Pfennig.ttf' (12)} 0xffffffff\n");
    }

    @Test
    public void shouldNotAskGraphicsOperationsToDrawNewlineCharacters() throws Exception {
        draw(new Rect(0, 0, 50, 0), new SceneText("Word\nWord\n", Font.PFENNIG, Color.WHITE));
        assertRenderingIs("" +
                "Text: \"Word\" 0.0, 13.0 Font{'Pfennig.ttf' (12)} 0xffffffff\n" +
                "Text: \"Word\" 0.0, 30.0 Font{'Pfennig.ttf' (12)} 0xffffffff\n");
    }

    @Test
    public void shouldStopAfterDrawingPastTheEndOfTheScreen() throws Exception {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("Word ");
        }
        subject = new SceneText(sb.toString(), Font.PFENNIG, Color.WHITE);
        draw(new Rect(0, 0, 50, 0), subject);
        assertThat(g.getRendering().split("\n").length, lessThan(2000));
    }
}
