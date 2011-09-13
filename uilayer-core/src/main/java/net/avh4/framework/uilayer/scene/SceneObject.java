package net.avh4.framework.uilayer.scene;


public abstract class SceneObject<G> {

	protected int x;
	protected int y;
	protected final int width;
	protected final int height;

	public SceneObject(final int x, final int y, final int width,
			final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void draw(G g);

}