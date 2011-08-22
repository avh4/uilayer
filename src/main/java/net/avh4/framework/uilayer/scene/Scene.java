package net.avh4.framework.uilayer.scene;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

public class Scene implements Iterable<SwingImage> {

	private final ArrayList<SwingImage> children = new ArrayList<SwingImage>();
	private final String title;

	public Scene(final String title) {
		this.title = title;
	}

	public Scene() {
		this("Untitled Scene");
	}

	public Dimension getBounds() {
		return new Dimension(800, 600);
	}

	public void addImage(final int x, final int y, final int width,
			final int height, final String imageName) {
		children.add(new SwingImage(x, y, width, height, imageName));
	}

	@Override
	public Iterator<SwingImage> iterator() {
		return children.iterator();
	}

	public String getTitle() {
		return title;
	}

}
