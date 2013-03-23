package net.avh4.framework.uilayer;


import net.avh4.framework.async.Promise;
import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.File;
import net.avh4.framework.uilayer.input.ClickReceiver;
import net.avh4.framework.uilayer.input.KeyReceiver;

import java.util.List;

public interface UILayerService {

    void run(Element game, ClickReceiver receiver, KeyReceiver keyReceiver, TimerUpdate timerUpdate);

    int getImageWidth(String image);

    int getImageHeight(String image);

    int getPixel(String image, int x, int y);

    int getFontHeight(Font font);

    int measureText(Font font, String text);

    <T> Promise<T> showChoices(String title, List<T> choices);

    ExternalStorage getExternalStorage();

    Promise<File> showFileChooser(String title);
}
