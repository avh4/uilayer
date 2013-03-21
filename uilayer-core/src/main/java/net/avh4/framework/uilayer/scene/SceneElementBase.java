package net.avh4.framework.uilayer.scene;

public abstract class SceneElementBase implements SceneElement {

    protected double x;
    protected double y;
    protected double width;
    protected double height;
    protected final String name;
    protected boolean hidden;

    public SceneElementBase(final String name, final double x, final double y,
                            final double width, final double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setWidth(final double width) {
        this.width = width;
    }

    public void setHeight(final double height) {
        this.height = height;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public void setY(final double y) {
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

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }
}