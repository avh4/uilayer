package net.avh4.framework.uilayer;

import net.avh4.demo.uilayer.DemoGame;
import net.avh4.framework.uilayer.android.UILayerActivity;

public class DemoGameActivity extends UILayerActivity {

	public DemoGameActivity() {
		super(new DemoGame());
	}
}