package net.avh4.framework.uilayer.swing.scene;

import net.avh4.framework.uilayer.scene.RenderTestBase;
import net.avh4.framework.uilayer.scene.SceneImage;
import net.avh4.framework.uilayer.swing.SwingUILayerService;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderImagesTest extends RenderTestBase {

    @Test
    public void testRenderProvidedImage() throws Exception {
        final BufferedImage image = new BufferedImage(50, 50,
                BufferedImage.TYPE_INT_ARGB);
        final Graphics g = image.getGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 50, 50);
        g.setColor(Color.PINK);
        g.fillRect(10, 10, 50, 50);
        g.dispose();

        SwingUILayerService.cacheImage("key", image);
        scene.add(new SceneImage(100, 100, 50, 50, "key"));
        assertRenderingIsApproved();
    }
}
