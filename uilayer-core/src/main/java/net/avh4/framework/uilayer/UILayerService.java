package net.avh4.framework.uilayer;


import java.util.List;

public interface UILayerService {

    void run(SceneCreator game, ClickReceiver receiver, KeyReceiver keyReceiver);

    int getImageWidth(String image);

    int getImageHeight(String image);

    int getFontHeight(Font font);

    int measureText(Font font, String text);

    void showChoices(String title, List<String> choices);
}
