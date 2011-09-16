package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;

public interface UILayerService {

	void run(SceneCreator game, ClickReceiver receiver, KeyReceiver keyReceiver);

	Scene newScene(String title);

}
