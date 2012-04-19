package net.avh4.framework.uilayer.scene;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SceneCenteredTextTest {

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
}
