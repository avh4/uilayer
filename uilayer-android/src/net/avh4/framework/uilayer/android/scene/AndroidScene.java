package net.avh4.framework.uilayer.android.scene;

import java.util.ArrayList;
import java.util.Iterator;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneImage;

public class AndroidScene implements Scene, Iterable<AndroidSceneObject> {

	private final ArrayList<AndroidSceneObject> children = new ArrayList<AndroidSceneObject>();
	private final String title;

	public AndroidScene(final String title) {
		this.title = title;
	}

	@Override
	public Iterator<AndroidSceneObject> iterator() {
		return children.iterator();
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void addText(final String text, final int x, final int y,
			final int width, final String customFontResource, final int fontSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPlaceholder(final String name, final int x, final int y,
			final int width, final int height) {
		children.add(new AndroidPlaceholder(name, x, y, width, height));
	}

	@Override
	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final String imageName, final int clipX,
			final int clipY, final int clipWidth, final int clipHeight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final String imageName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		return 200;
	}

	@Override
	public int getHeight() {
		return 200;
	}

}
