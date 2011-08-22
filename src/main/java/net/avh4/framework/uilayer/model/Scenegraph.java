package net.avh4.framework.uilayer.model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

public class Scenegraph implements Iterable<Image> {

	private final ArrayList<Image> children = new ArrayList<Image>();
	private final String title;

	public Scenegraph(final String title) {
		this.title = title;
	}

	public Scenegraph() {
		this("Untitled Scene");
	}

	public Dimension getBounds() {
		return new Dimension(800, 600);
	}

	public void addImage(final int x, final int y, final int width,
			final int height, final String imageName) {
		children.add(new Image(x, y, width, height, imageName));
	}

	@Override
	public Iterator<Image> iterator() {
		return children.iterator();
	}

	public String getTitle() {
		return title;
	}

}
