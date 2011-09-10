package net.avh4.framework.uilayer.scene;

import java.awt.Dimension;

public interface Scene {

	String getTitle();

	Dimension getBounds();

	public abstract void addText(final String text, final int x, final int y,
			final int width, final String customFontResource, final int fontSize);

	public abstract void addPlaceholder(final String name, final int x,
			final int y, final int width, final int height);

	public abstract SceneImage addImage(final int x, final int y,
			final int width, final int height, final String imageName,
			final int clipX, final int clipY, final int clipWidth,
			final int clipHeight);

	public abstract SceneImage addImage(final int x, final int y,
			final int width, final int height, final String imageName);

}
