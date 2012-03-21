package net.avh4.framework.uilayer.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import net.avh4.framework.uilayer.R;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.scene.AndroidSceneRenderer;

public class AndroidSceneRendererActivity extends Activity {

	private UI mUi;

	public AndroidSceneRendererActivity() {
        super();
        mUi = null;
    }

	public AndroidSceneRendererActivity(final UI ui) {
        super();
        mUi = ui;
    }

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setUI(mUi);
	}

	public void setUI(final UI ui) {
		mUi = ui;
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (mUi != null) {
					setContentView(new AndroidSceneRenderer(AndroidSceneRendererActivity.this, mUi));
				} else {
					final ImageView placeholder = new ImageView(
							AndroidSceneRendererActivity.this);
					placeholder.setImageResource(R.drawable.icon);
					setContentView(placeholder);
				}
			}
		});
	}

}
