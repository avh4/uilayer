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
}
