package net.avh4.framework.uilayer.android;

import android.test.InstrumentationTestCase;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UILayerService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AndroidUILayerServiceTest extends InstrumentationTestCase {
    private UILayerService service;

    public void setUp() throws Exception {
        super.setUp();
        service = new AndroidUILayerService();
        ((AndroidUILayerService) service).init(getInstrumentation().getTargetContext());
    }

    public void testGetFontHeight() throws Exception {
        assertThat(service.getFontHeight(Font.PFENNIG.size(16)), is(22));
    }

    public void testMeasureText() throws Exception {
        final int ANDROID_ADJUST = -1;
        assertThat(service.measureText(Font.PFENNIG.size(16), "quick"), is(36+ANDROID_ADJUST));
    }

}
