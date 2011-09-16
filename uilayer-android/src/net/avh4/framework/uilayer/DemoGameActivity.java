package net.avh4.framework.uilayer;

import net.avh4.demo.uilayer.DemoGame;
import net.avh4.framework.uilayer.android.AndroidSceneRendererActivity;

public class DemoGameActivity extends AndroidSceneRendererActivity {

	public DemoGameActivity() {
		super(new DemoGame());
	}
}