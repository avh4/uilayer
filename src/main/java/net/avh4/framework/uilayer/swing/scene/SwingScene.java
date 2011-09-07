package net.avh4.framework.uilayer.swing.scene;

import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneImage;

public class SwingScene implements Iterable<SwingSceneObject>, Scene {

	private final ArrayList<SwingSceneObject> children = new ArrayList<SwingSceneObject>();
	private final String title;

	public SwingScene(final String title) {
		this.title = title;
	}

	public SwingScene() {
		this("Untitled Scene");
	}

	@Override
	public Dimension getBounds() {
		return new Dimension(800, 600);
	}

	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final String imageName) {
		final SwingImage image = new SwingImage(x, y, width, height, imageName);
		children.add(image);
		return image;
	}

	public void addImage(final int x, final int y, final int width,
			final int height, final Image image) {
		children.add(new SwingImage(x, y, width, height, image));
	}

	@Override
	public Iterator<SwingSceneObject> iterator() {
		return children.iterator();
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void addPlaceholder(final String name, final int x, final int y,
			final int width, final int height) {
		children.add(new SwingPlaceholder(name, x, y, width, height));
	}

	public void addText(final String text, final int x, final int y,
			final int width, final String customFontResource, final int fontSize) {
		children.add(new SwingText(text, x, y, width, customFontResource,
				fontSize));
	}

}
