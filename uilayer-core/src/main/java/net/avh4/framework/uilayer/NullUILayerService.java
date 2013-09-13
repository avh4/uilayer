package net.avh4.framework.uilayer;

import net.avh4.framework.async.Promise;
import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.File;
import net.avh4.framework.uilayer.input.ClickReceiver;
import net.avh4.framework.uilayer.input.KeyReceiver;
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
    public void run(Element game, ClickReceiver receiver, KeyReceiver keyReceiver, TimerUpdate timerUpdate) {
        throw exception;
    }

    @Override
    public Image loadImageResource(String resourceName) {
        throw exception;
    }

    @Override
    public Image loadImageFile(String filename) {
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
    public <T> Promise<T> showChoices(String title, List<T> choices) {
        throw exception;
    }

    @Override
    public ExternalStorage getExternalStorage() {
        throw exception;
    }

    @Override
    public Promise<File> showFileChooser(String title) {
        throw exception;
    }
}
