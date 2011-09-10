package net.avh4.framework.uilayer.android;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.KeyReceiver;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.android.scene.AndroidScene;
import net.avh4.framework.uilayer.scene.Scene;

public class AndroidUILayerService implements UILayerService {

	@Override
	public void main(final SceneCreator arg0, final ClickReceiver arg1,
			final KeyReceiver arg2) {
		throw new RuntimeException(
				"Android applications do not implement a main entry point.  Subclass UILayerActivity instead.");
	}

	@Override
	public Scene newScene(final String title) {
		return new AndroidScene(title);
	}

}
