package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UILayerService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class SwingUILayerServiceTest {

    private UILayerService service;

    @Before
    public void setUp() throws Exception {
        service = new SwingUILayerService();
    }

    @Test
    public void testGetImageWidth() throws Exception {
        assertThat(service.getImageWidth("tile1.png"), is(101));
    }

    @Test
    public void testGetImageHeight() throws Exception {
        assertThat(service.getImageHeight("tile1.png"), is(101));
    }

    @Test
    public void testGetFontHeight() throws Exception {
        assertThat(service.getFontHeight(Font.PFENNIG.size(16)), is(22));
    }

    @Test
    public void testMeasureText() throws Exception {
        assertThat(service.measureText(Font.PFENNIG.size(16), "quick"), is(36));
    }
}
