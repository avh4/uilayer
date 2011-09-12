package net.avh4.framework.uilayer.android;

import net.avh4.framework.uilayer.UI;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class UILayerActivity extends Activity {

	private final UI mUi;

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
		setContentView(new UILayerView(this, mUi));
	}

}
