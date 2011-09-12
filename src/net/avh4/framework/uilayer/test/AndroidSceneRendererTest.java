package net.avh4.framework.uilayer.test;

import static net.avh4.util.imagecomparison.android.Matchers.isApproved;
import static org.hamcrest.MatcherAssert.assertThat;
import net.avh4.framework.uilayer.android.UILayerActivity;
import android.test.ActivityInstrumentationTestCase2;

public class AndroidSceneRendererTest extends
		ActivityInstrumentationTestCase2<UILayerActivity> {

	public AndroidSceneRendererTest() {
		super(UILayerActivity.class);
	}

	public void testRenderEmptyScene() {
		assertThat(getActivity(), isApproved(getInstrumentation().getContext()));
	}

}
