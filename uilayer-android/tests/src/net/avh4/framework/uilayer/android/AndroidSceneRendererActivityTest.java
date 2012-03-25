package net.avh4.framework.uilayer.android;

import android.app.Activity;
import junit.framework.TestCase;
import net.avh4.framework.uilayer.UILayer;

public class AndroidSceneRendererActivityTest extends TestCase {

    public void testShouldSetServiceContext() throws Exception {
        final Activity activity = new AndroidSceneRendererActivity();
        assertNotNull(((AndroidUILayerService) UILayer.service).context);
    }
}
