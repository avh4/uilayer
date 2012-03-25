package net.avh4.framework.uilayer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.stub;

public class FontTest {

    private Font subject;

    @Before
    public void setUp() throws Exception {
        Font.service = Mockito.mock(UILayerService.class);
        
        subject = new Font("MyFont.ttf");

        stub(Font.service.getFontHeight(subject)).toReturn(20);
        stub(Font.service.measureText(subject, "XYZZY")).toReturn(67);
        stub(Font.service.measureText(subject.size(24), "XYZZY")).toReturn(240);
    }

    @Test
    public void getResourceName() throws Exception {
        assertThat(subject.getResourceName(), is("MyFont.ttf"));
    }

    @Test
    public void getHeight() throws Exception {
        assertThat(subject.getHeight(), is(20));
    }

    @Test
    public void measureText() throws Exception {
        assertThat(subject.measureText("XYZZY"), is(67));
        assertThat(subject.size(24).measureText("XYZZY"), is(240));
    }

    @Test
    public void size_shouldSetSize() throws Exception {
        assertThat(subject.size(72).getSize(), is(72));
    }

    @Test
    public void size_shouldNotMutate() throws Exception {
        final Font sizedFont = subject.size(84);
        assertThat(subject.getSize(), not(84));
    }

    @Test
    public void size_defaultValue() throws Exception {
        assertThat(subject.getSize(), is(12));
    }
    
}
