package net.avh4.framework.uilayer;


import net.avh4.framework.data.ExternalStorage;

public class UILayer {

    private static final String SWING_SERVICE = "net.avh4.framework.uilayer.swing.SwingUILayerService";
    private static final String ANDROID_SERVICE = "net.avh4.framework.uilayer.android.AndroidUILayerService";
    @SuppressWarnings("unused")
    private static final String IOS_SERVICE = "net.avh4.framework.uilayer.ios.IOSUILayerService";

    // IOS_SERVICE is not added here because xmlvm does not implement
    // ClassLoader for iOS. Therefore we must replace this entire class in
    // uilayer-ios-xmlvm and not rely on dynamic loading.
    static final String[] KNOWN_SERVICES = new String[]{
            SWING_SERVICE, ANDROID_SERVICE
    };

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

        return new NullUILayerService();
    }

    private static UILayerService loadService(final String serviceName) {
        try {
            final Class<?> clazz = UILayer.class.getClassLoader().loadClass(serviceName);
            return (UILayerService) clazz.newInstance();
        } catch (final ClassNotFoundException e) {
            return null;
        } catch (final InstantiationException e) {
            throw new RuntimeException(String.format(
                    "Could not instantiate %s", serviceName), e);
        } catch (final IllegalAccessException e) {
            throw new RuntimeException(String.format(
                    "Could not instantiate %s", serviceName), e);
        }
    }

    public static void main(final SceneCreator game, final ClickReceiver receiver, final KeyReceiver keyReceiver) {
        service.run(game, receiver, keyReceiver);
    }

    public static ExternalStorage getExternalStorage() {
        return service.getExternalStorage();
    }
}
