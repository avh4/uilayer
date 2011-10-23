package net.avh4.framework.uilayer;


public interface UILayerService {

	void run(SceneCreator game, ClickReceiver receiver, KeyReceiver keyReceiver);

	int getImageWidth(String image);

	int getImageHeight(String image);

}
