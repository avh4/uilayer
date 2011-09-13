package net.avh4.framework.uilayer.test;

import static org.hamcrest.MatcherAssert.assertThat;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.android.UILayerActivity;
import net.avh4.framework.uilayer.scene.Scene;

import org.hamcrest.Matcher;

import android.test.ActivityInstrumentationTestCase2;

public class AndroidSceneRendererTest extends
		ActivityInstrumentationTestCase2<UILayerActivity> {

	private UI mUi;
	private Scene mScene;

	public AndroidSceneRendererTest() {
		super(UILayerActivity.class);
	}

	@Override
	public void setUp() {
		mScene = UILayer.newScene("AndroidSceneRendererTest");
		mUi = new UI() {
			public void key(final int arg0) {
			}

			public void click(final int arg0, final int arg1) {
			}

			public Scene getScene() {
				return mScene;
			}
		};
		getActivity().setUI(mUi);
	}

	public void testRenderEmptyScene() {
		assertThat(getActivity(), isApproved());
	}

	public void testRenderNullScene() {
		mScene = null;
		assertThat(getActivity(), isApproved());
	}

	private Matcher<? super UILayerActivity> isApproved() {
		return net.avh4.util.imagecomparison.android.Matchers
				.isApproved(getInstrumentation().getContext());
	}

}
