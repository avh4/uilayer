package net.avh4.framework.uilayer;


import org.apache.commons.lang.StringUtils;

public class UILayer {

    private static final String SWING_SERVICE = "net.avh4.framework.uilayer.swing.SwingUILayerService";
    private static final String ANDROID_SERVICE = "net.avh4.framework.uilayer.android.AndroidUILayerService";
    @SuppressWarnings("unused")
    private static final String IOS_SERVICE = "net.avh4.framework.uilayer.ios.IOSUILayerService";

    // IOS_SERVICE is not added here because xmlvm does not implement
    // ClassLoader for iOS. Therefore we must replace this entire class in
    // uilayer-ios-xmlvm and not rely on dynamic loading.
    private static final String[] KNOWN_SERVICES = new String[]{
            SWING_SERVICE, ANDROID_SERVICE};

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

        return new UILayerService() {
            public RuntimeException exception = new RuntimeException(
                    String.format(
                            "Could not find a known UILayerService on the classpath.\n" +
                                    "If you are using maven, your project must include uilayer-swing as " +
                                    "a runtime dependency.\n\nChecked for: \n>>%s\n",
                            StringUtils.join(KNOWN_SERVICES, "\n>>")));

            @Override
            public void run(SceneCreator game, ClickReceiver receiver, KeyReceiver keyReceiver) {
                throw exception;
            }

            @Override
            public int getImageWidth(String image) {
                throw exception;
            }

            @Override
            public int getImageHeight(String image) {
                throw exception;
            }

            @Override
            public int getFontHeight(Font font) {
                throw exception;
            }

            @Override
            public int measureText(Font font, String text) {
                throw exception;
            }
        };
    }

    private static UILayerService loadService(final String serviceName) {
        try {
            final Class<?> clazz = UILayer.class.getClassLoader().loadClass(
                    serviceName);
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

    public static void main(final SceneCreator game,
                            final ClickReceiver receiver, final KeyReceiver keyReceiver) {
        service.run(game, receiver, keyReceiver);
    }
}
