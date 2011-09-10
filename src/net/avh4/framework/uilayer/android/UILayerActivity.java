package net.avh4.framework.uilayer.android;

import net.avh4.framework.uilayer.UI;
import android.app.Activity;
import android.os.Bundle;

public class UILayerActivity extends Activity {

	private final UI mUi;

	public UILayerActivity(final UI ui) {
		mUi = ui;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new UILayerView(this, mUi));
	}

}
