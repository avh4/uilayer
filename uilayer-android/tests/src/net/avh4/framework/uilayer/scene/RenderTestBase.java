package net.avh4.framework.uilayer.scene;

import android.test.ActivityInstrumentationTestCase2;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.android.AndroidSceneRendererActivity;
import net.avh4.util.imagecomparison.android.Matchers;
import org.hamcrest.Matcher;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public abstract class RenderTestBase extends ActivityInstrumentationTestCase2<AndroidSceneRendererActivity> {

    protected Scene scene;
    private UI ui;

    public RenderTestBase() {
        super(AndroidSceneRendererActivity.class);
    }


    public void setUp() {
        scene = new Scene();
        ui = new MockUI();
        getActivity().setUI(ui);
    }

    protected void assertRenderingIsApproved() throws IOException {
        assertThat(getActivity(), isApproved());
    }

    private Matcher<? super AndroidSceneRendererActivity> isApproved() {
        return Matchers.isApproved(getInstrumentation().getContext());
    }

    private class MockUI implements UI {
        public void key(final int arg0) {
        }

        public void click(final int arg0, final int arg1) {
        }

        public Scene getScene() {
            return scene;
        }
    }
}
