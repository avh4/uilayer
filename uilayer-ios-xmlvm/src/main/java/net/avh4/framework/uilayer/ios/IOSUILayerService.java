package net.avh4.framework.uilayer.ios;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.KeyReceiver;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.scene.Scene;

import org.xmlvm.iphone.UIApplication;

public class IOSUILayerService implements UILayerService {

	@Override
	public void run(final SceneCreator game, final ClickReceiver receiver,
			final KeyReceiver keyReceiver) {
		UILayerAppDelegate.setNextUi((UI) game);
		UIApplication.main(null, null, UILayerAppDelegate.class);
	}

	@Override
	public Scene newScene(final String title) {
		return new IOSScene(title);
	}

}
