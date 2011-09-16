package net.avh4.framework.uilayer.ios;

import java.util.ArrayList;
import java.util.Iterator;

import net.avh4.framework.uilayer.SceneBase;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneImage;
import net.avh4.framework.uilayer.scene.SceneObject;

import org.xmlvm.iphone.CGRect;

public class IOSScene extends SceneBase implements Scene,
		Iterable<SceneObject<CGRect>> {

	private final ArrayList<SceneObject<CGRect>> children = new ArrayList<SceneObject<CGRect>>();

	public IOSScene(final String title) {
		super(title);
	}

	@Override
	public Iterator<SceneObject<CGRect>> iterator() {
		return children.iterator();
	}

	@Override
	public void addText(final String text, final int x, final int y,
			final int width, final String customFontResource, final int fontSize) {
		addPlaceholder(text, x, y, width, 10);
	}

	@Override
	public void addPlaceholder(final String name, final int x, final int y,
			final int width, final int height) {
		children.add(new IOSPlaceholder(name, x, y, width, height));
	}

	@Override
	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final String imageName, final int clipX,
			final int clipY, final int clipWidth, final int clipHeight) {
		addPlaceholder(imageName, x, y, width, height);
		return null;
	}

	@Override
	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final String imageName) {
		addPlaceholder(imageName, x, y, width, height);
		return null;
	}

}
