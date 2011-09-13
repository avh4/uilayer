package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;

public class UILayer {

	private static final String SWING_SERVICE = "net.avh4.framework.uilayer.swing.SwingUILayerService";
	private static final String ANDROID_SERVICE = "net.avh4.framework.uilayer.android.AndroidUILayerService";

	private static final String[] KNOWN_SERVICES = new String[] {
			SWING_SERVICE, ANDROID_SERVICE };

	public static final UILayerService service = loadService();

	public static void main(final UI game) {
		UILayer.main(game, game, game);
	}

	private static UILayerService loadService() {
		for (final String serviceName : KNOWN_SERVICES) {
			final UILayerService service = loadService(serviceName);
			if (service != null) {
				return service;
			}
		}
		throw new RuntimeException(
				String.format(
						"Could not find a known UILayerService on the classpath (checked for %s).  If you are using maven, your project must include uilayer-swing as a runtime dependency.",
						(Object) KNOWN_SERVICES));
	}

	private static UILayerService loadService(final String serviceName) {
		try {
			final Class<?> clazz = UILayer.class.getClassLoader().loadClass(
					serviceName);
			return (UILayerService) clazz.newInstance();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (final InstantiationException e) {
			throw new RuntimeException(String.format(
					"Could not instantiate %s", serviceName), e);
		} catch (final IllegalAccessException e) {
			throw new RuntimeException(String.format(
					"Could not instantiate %s", serviceName), e);
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
