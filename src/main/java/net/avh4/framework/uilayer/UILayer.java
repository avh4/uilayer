package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;

public class UILayer {

	public static final UILayerService service = loadService();
	private static final String SWING_SERVICE = "net.avh4.framework.uilayer.swing.SwingUILayerService";

	public static void main(final UI game) {
		UILayer.main(game, game, game);
	}

	private static UILayerService loadService() {
		try {
			final Class<?> clazz = UILayer.class.getClassLoader().loadClass(
					SWING_SERVICE);
			return (UILayerService) clazz.newInstance();
		} catch (final ClassNotFoundException e) {
			throw new RuntimeException(
					String.format(
							"Could not find %s on the classpath.  If you are using maven, your project must include uilayer-swing as a runtime dependency.",
							SWING_SERVICE), e);
		} catch (final InstantiationException e) {
			throw new RuntimeException(String.format(
					"Could not instantiate %s", SWING_SERVICE), e);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(String.format(
					"Could not instantiate %s", SWING_SERVICE), e);
		}
	}

	public static Scene newScene(final String title) {
		return service.newScene(title);
	}

	public static void main(final SceneCreator game,
			final ClickReceiver receiver, final KeyReceiver keyReceiver) {
		service.main(game, receiver, keyReceiver);
	}

}
