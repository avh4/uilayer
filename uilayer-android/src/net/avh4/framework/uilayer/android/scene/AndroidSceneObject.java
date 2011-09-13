package net.avh4.framework.uilayer.android.scene;

import android.graphics.Canvas;

public abstract class AndroidSceneObject {

	protected int x;
	protected int y;
	protected final int width;
	protected final int height;

	public AndroidSceneObject(final int x, final int y, final int width,
			final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void draw(Canvas g);
}
