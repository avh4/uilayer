package net.avh4.framework.uilayer.android;

import net.avh4.framework.uilayer.R;
import net.avh4.framework.uilayer.UI;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class UILayerActivity extends Activity {

	private UI mUi;

	public UILayerActivity() {
		mUi = null;
	}

	public UILayerActivity(final UI ui) {
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
					setContentView(new UILayerView(UILayerActivity.this, mUi));
				} else {
					final ImageView placeholder = new ImageView(
							UILayerActivity.this);
					placeholder.setImageResource(R.drawable.icon);
					setContentView(placeholder);
				}
			}
		});
	}

}
