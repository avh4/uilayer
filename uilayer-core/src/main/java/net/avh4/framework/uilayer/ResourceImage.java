package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.GraphicsOperations;

public class ResourceImage implements Image {
    static UILayerService service = UILayer.service;
    private final String imageName;

    public ResourceImage(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String getName() {
        return imageName; // TODO: do we really want this?
    }

    @Override
    public int getWidth() {
        return service.getImageWidth(imageName);
    }

    @Override
    public int getHeight() {
        return service.getImageHeight(imageName);
    }

    @Override
    public void drawImage(GraphicsOperations g, int clipX, int clipY, int clipWidth, int clipHeight,
                          int width, int height) {
        g.drawImage(imageName, 0, 0, width, height, clipX, clipY, clipX + clipWidth, clipY + clipHeight);
    }
}
