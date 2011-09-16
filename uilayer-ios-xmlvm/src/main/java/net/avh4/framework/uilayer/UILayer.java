package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.ios.IOSUILayerService;
import net.avh4.framework.uilayer.scene.Scene;

/**
 * This class replaces the version in uilayer-core when compiling for xmlvm
 * because the uilayer-core version uses ClassLoader, which is not implemented
 * for iOS with xmlvm.
 */
public class UILayer {

	public static final UILayerService service = loadService();

	public static void run(final UI game) {
		UILayer.run(game, game, game);
	}

	private static UILayerService loadService() {
		return new IOSUILayerService();
	}

	public static Scene newScene(final String title) {
		return service.newScene(title);
	}

	public static void run(final SceneCreator game,
			final ClickReceiver receiver, final KeyReceiver keyReceiver) {
		service.run(game, receiver, keyReceiver);
	}

}
