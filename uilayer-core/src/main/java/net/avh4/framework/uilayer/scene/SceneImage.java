package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Image;
import net.avh4.framework.uilayer.ResourceImage;

public class SceneImage extends SceneElementBase {
    protected Image image;
    protected int clipX;
    protected int clipY;
    protected int clipWidth;
    protected int clipHeight;

    public SceneImage(final int x, final int y, final int width,
                      final int height, final Image image, final int clipX,
                      final int clipY, final int clipWidth, final int clipHeight) {
        super(image.getName(), x, y, width, height);
        this.image = image;
        this.clipX = clipX;
        this.clipY = clipY;
        this.clipWidth = clipWidth;
        this.clipHeight = clipHeight;
    }

    public SceneImage(final int x, final int y, final int width, final int height, final Image image) {
        this(x, y, width, height, image, 0, 0, image.getWidth(), image.getHeight());
    }

    public SceneImage(int x, int y, Image image) {
        this(x, y, image.getWidth(), image.getHeight(), image);
    }

    /**
     * @param imageName The image specified must exist on the classpath in the default
     *                  package.
     */
    public SceneImage(final int x, final int y, final int width, final int height, final String imageName) {
        this(x, y, width, height, new ResourceImage(imageName));
    }

    /**
     * @param imageName The image specified must exist on the classpath in the default
     *                  package.
     */
    public SceneImage(final int x, final int y, final int width,
                      final int height, final String imageName, final int clipX,
                      final int clipY, final int clipWidth, final int clipHeight) {
        this(x, y, width, height, new ResourceImage(imageName), clipX, clipY, clipWidth, clipHeight);
    }

    public SceneImage(int x, int y, int width, int height) {
        super("Missing Image", x, y, width, height);
        this.image = null;
        this.clipX = 0;
        this.clipY = 0;
        this.clipWidth = 0;
        this.clipHeight = 0;
    }

    public void setPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void setClipPosition(final int clipX, final int clipY) {
        this.clipX = clipX;
        this.clipY = clipY;
    }

    @Override
    public void draw(GraphicsOperations g, FontMetricsService fm) {
        if (image == null) {
            new ScenePlaceholder(name, x, y, width, height).draw(g, fm);
        } else {
            g.translate(x, y);
            // TODO: use a scale transform instead so that the image doesn't need to care about the
            // destination width/height ?
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
