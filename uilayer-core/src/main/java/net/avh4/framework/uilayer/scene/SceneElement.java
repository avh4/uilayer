package net.avh4.framework.uilayer.scene;

public abstract class SceneElement {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected final String name;
    protected boolean visible = true;

    public SceneElement(final String name, final int x, final int y,
			final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getX() {
		return x;
	}

	public int getHeight() {
		return height;
	}

	public int getY() {
		return y;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public void setY(final int y) {
		this.y = y;
	}

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }
}