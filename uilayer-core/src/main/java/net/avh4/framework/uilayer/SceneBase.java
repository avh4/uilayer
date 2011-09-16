package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.Scene;

public abstract class SceneBase implements Scene {

	protected final String title;
	private int width = 800;
	private int height = 600;

	public SceneBase(final String title) {
		this.title = title;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setSize(final int width, final int height) {
		this.width = width;
		this.height = height;
	}

}