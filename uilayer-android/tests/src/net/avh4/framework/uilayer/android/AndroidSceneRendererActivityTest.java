package net.avh4.framework.uilayer.android;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import org.easymock.EasyMock;

public class AndroidSceneRendererActivityTest extends ActivityInstrumentationTestCase2<AndroidSceneRendererActivity> {

    private AndroidSceneRendererActivity activity;
    private UI ui;

    public AndroidSceneRendererActivityTest() {
        super(AndroidSceneRendererActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        ui = EasyMock.createMock(UI.class);
        EasyMock.expect(ui.getScene()).andStubReturn(null);

        activity = getActivity();
        activity.setUI(ui);
    }

    public void testShouldSetServiceContext() throws Exception {
        assertNotNull(((AndroidUILayerService) UILayer.service).context);
    }

    public void testDispatchClick() {
        ui.click(360, 592);
        EasyMock.replay(ui);

        TouchUtils.clickView(this, activity.getWindow().getDecorView());

        try {
            EasyMock.verify(ui);
        } catch (AssertionError e) {
            throw new RuntimeException("Verification of touch events failed.  Is the device's screen unlocked?", e);
        }
    }
}
