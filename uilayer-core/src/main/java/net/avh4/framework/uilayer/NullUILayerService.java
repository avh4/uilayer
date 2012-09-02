package net.avh4.framework.uilayer;

import org.apache.commons.lang.StringUtils;

import java.util.List;

class NullUILayerService implements UILayerService {
    public RuntimeException exception = new RuntimeException(
            String.format(
                    "Could not find a known UILayerService on the classpath.\n" +
                            "If you are using maven, your project must include uilayer-swing as " +
                            "a runtime dependency.\n\nChecked for: \n>>%s\n",
                    StringUtils.join(UILayer.KNOWN_SERVICES, "\n>>")));

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
    public int getPixel(String image, int x, int y) {
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

    @Override
    public <T> void showChoices(String title, List<T> choices, ResponseListener<T> listener) {
        throw exception;
    }
}
