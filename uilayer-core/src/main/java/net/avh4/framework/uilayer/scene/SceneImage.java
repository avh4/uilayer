package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.ResourceImage;
import net.avh4.math.geometry.Rect;

public class SceneImage implements Element {
    protected Image image;
    protected int clipX;
    protected int clipY;
    protected int clipWidth;
    protected int clipHeight;

    public SceneImage(final Image image, final int clipX,
                      final int clipY, final int clipWidth, final int clipHeight) {
        this.image = image;
        this.clipX = clipX;
        this.clipY = clipY;
        this.clipWidth = clipWidth;
        this.clipHeight = clipHeight;
    }

    public SceneImage(final Image image) {
        this(image, 0, 0, image.getWidth(), image.getHeight());
    }

    /**
     * @param imageName The image specified must exist on the classpath in the default
     *                  package.
     */
    public SceneImage(final String imageName) {
        this(new ResourceImage(imageName));
    }

    /**
     * @param imageName The image specified must exist on the classpath in the default
     *                  package.
     */
    public SceneImage(final String imageName, final int clipX,
                      final int clipY, final int clipWidth, final int clipHeight) {
        this(new ResourceImage(imageName), clipX, clipY, clipWidth, clipHeight);
    }

    public SceneImage() {
        this.image = null;
        this.clipX = 0;
        this.clipY = 0;
        this.clipWidth = 0;
        this.clipHeight = 0;
    }

    public void setClipPosition(final int clipX, final int clipY) {
        this.clipX = clipX;
        this.clipY = clipY;
    }

    @Override
    public void draw(Rect bounds, GraphicsOperations g, FontMetricsService fm) {
        double x = bounds.minX();
        double y = bounds.minY();
        double width = bounds.width();
        double height = bounds.height();
        if (image == null) {
            new ScenePlaceholder("Missing Image").draw(Rect.fromTopLeft(x, y, width, height), g, fm);
        } else {
            g.translate(x, y);
            image.drawImage(g, clipX, clipY, clipWidth, clipHeight, width, height);
            g.translate(-x, -y);
        }
    }

    public void setImage(Image image) {
        this.image = image;
        this.clipX = 0;
        this.clipY = 0;
        this.clipWidth = image.getWidth();
        this.clipHeight = image.getHeight();
    }
}
