package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.UILayerService;

public class SceneImage extends SceneElement {
    static UILayerService service = UILayer.service;

    protected final String image;
    protected int clipX;
    protected int clipY;
    protected final int clipWidth;
    protected final int clipHeight;

    /**
     * @param imageName The image specified must exist on the classpath in the default
     *                  package.
     */
    public SceneImage(final int x, final int y, final int width,
                      final int height, final String imageName) {
        super(imageName, x, y, width, height);
        image = imageName;
        clipX = 0;
        clipY = 0;
        clipWidth = service.getImageWidth(image);
        clipHeight = service.getImageHeight(image);
    }

    /**
     * @param imageName The image specified must exist on the classpath in the default
     *                  package.
     */
    public SceneImage(final int x, final int y, final int width,
                      final int height, final String imageName, final int clipX,
                      final int clipY, final int clipWidth, final int clipHeight) {
        super(imageName, x, y, width, height);
        image = imageName;
        this.clipX = clipX;
        this.clipY = clipY;
        this.clipWidth = clipWidth;
        this.clipHeight = clipHeight;
    }

    public void setPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void setClipPosition(final int clipX, final int clipY) {
        this.clipX = clipX;
        this.clipY = clipY;
    }

}
