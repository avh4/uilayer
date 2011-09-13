package net.avh4.framework.uilayer.swing.scene;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneImage;
import net.avh4.framework.uilayer.scene.SceneObject;

public class SwingScene implements Iterable<SceneObject<Graphics>>, Scene {

	private final ArrayList<SceneObject<Graphics>> children = new ArrayList<SceneObject<Graphics>>();
	private final String title;

	public SwingScene(final String title) {
		this.title = title;
	}

	public SwingScene() {
		this("Untitled Scene");
	}

	@Override
	public int getWidth() {
		return 800;
	}

	@Override
	public int getHeight() {
		return 600;
	}

	@Override
	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final String imageName) {
		final SwingImage image = new SwingImage(x, y, width, height, imageName);
		children.add(image);
		return image;
	}

	@Override
	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final String imageName, final int clipX,
			final int clipY, final int clipWidth, final int clipHeight) {
		final SwingImage image = new SwingImage(x, y, width, height, imageName,
				clipX, clipY, clipWidth, clipHeight);
		children.add(image);
		return image;
	}

	public void addImage(final int x, final int y, final int width,
			final int height, final Image image) {
		children.add(new SwingImage(x, y, width, height, image));
	}

	public SceneImage addImage(final int x, final int y, final int width,
			final int height, final Image image, final int clipX,
			final int clipY, final int clipWidth, final int clipHeight) {
		final SwingImage image2 = new SwingImage(x, y, width, height, image,
				clipX, clipY, clipWidth, clipHeight);
		children.add(image2);
		return image2;

	}

	@Override
	public Iterator<SceneObject<Graphics>> iterator() {
		return children.iterator();
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void addPlaceholder(final String name, final int x, final int y,
			final int width, final int height) {
		children.add(new SwingPlaceholder(name, x, y, width, height));
	}

	@Override
	public void addText(final String text, final int x, final int y,
			final int width, final String customFontResource, final int fontSize) {
		children.add(new SwingText(text, x, y, width, customFontResource,
				fontSize));
	}

}
