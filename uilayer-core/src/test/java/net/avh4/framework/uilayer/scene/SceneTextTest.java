package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UILayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SceneTextTest {
    private SceneText subjectWithTextAsName;
    private SceneText subject;

    @Before
    public void setup() throws Exception {
        Font.service = Mockito.mock(UILayerService.class);
        Mockito.stub(Font.service.getFontHeight(Font.PFENNIG)).toReturn(137);

        subject = new SceneText("Text box", "Text to display", 0, 0, 0, Font.PFENNIG, 0);
        subjectWithTextAsName = new SceneText("Text to display", 0, 0, 0, Font.PFENNIG, 0);
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
    public void shouldHaveName() throws Exception {
        assertThat(subject.getName(), is("Text box"));
    }

    @Test
    public void shouldHaveText() throws Exception {
        assertThat(subject.getText(), is("Text to display"));
    }

    @Test
    public void withNoExplicitNameSpecified_shouldHaveTextAsName() throws Exception {
        assertThat(subjectWithTextAsName.getName(), is("Text to display"));
    }

    @Test
    public void shouldHaveHeightOfOneLineOfText() throws Exception {
        assertThat(subject.getHeight(), is(Font.PFENNIG.getLineHeight()));
    }
}
