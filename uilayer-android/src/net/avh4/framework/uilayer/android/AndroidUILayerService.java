package net.avh4.framework.uilayer.android;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.KeyReceiver;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayerService;

public class AndroidUILayerService implements UILayerService {

    @Override
    public void run(SceneCreator game, ClickReceiver receiver, KeyReceiver keyReceiver) {
        throw new RuntimeException(
      				"Android applications do not implement a main entry point.  " +
                              "Subclass AndroidSceneRendererActivity instead.");
    }

    @Override
    public int getImageWidth(String image) {
        return 0;
    }

    @Override
    public int getImageHeight(String image) {
        return 0;
    }
}
