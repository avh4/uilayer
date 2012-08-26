package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.UILayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings({"LawOfDemeter", "NestedMethodCall", "ChainedMethodCall"})
public class SceneImageTest extends RenderTestBase {

    private static final int TILE1_WIDTH = 101;
    private static final int TILE1_HEIGHT = 101;
    private static final int TILE2_WIDTH = 94;
    private static final int TILE2_HEIGHT = 94;

    @Before
    public void setup() throws Exception {
        SceneImage.service = Mockito.mock(UILayerService.class);
        Mockito.stub(SceneImage.service.getImageWidth("tile1.png")).toReturn(TILE1_WIDTH);
        Mockito.stub(SceneImage.service.getImageHeight("tile1.png")).toReturn(TILE1_HEIGHT);
        Mockito.stub(SceneImage.service.getImageWidth("tile2.png")).toReturn(TILE2_WIDTH);
        Mockito.stub(SceneImage.service.getImageHeight("tile2.png")).toReturn(TILE2_HEIGHT);
    }

    @Test
    public void shouldSetInitialClipToImageSize() throws Exception {
        SceneImage tile1 = new SceneImage(0, 0, 0, 0, "tile1.png");
        assertThat(tile1.clipX, is(0));
        assertThat(tile1.clipY, is(0));
        assertThat(tile1.clipWidth, is(TILE1_WIDTH));
        assertThat(tile1.clipHeight, is(TILE1_HEIGHT));
    }

    @Test
    public void testRenderResourceImage() throws Exception {
        scene.add(new SceneImage(100, 100, 50, 50, "tile1.png"));
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Image: 100, 100, 150, 150\n" +
                "    from \"tile1.png\": 0, 0, 101, 101\n");
    }

    @Test
    public void testRenderMultipleImages() throws Exception {
        scene.add(new SceneImage(100, 100, 150, 150, "tile1.png"));
        scene.add(new SceneImage(150, 150, 75, 75, "tile2.png"));
        scene.add(new SceneImage(100, 300, 16, 16, "tile1.png"));
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Image: 100, 100, 250, 250\n" +
                "    from \"tile1.png\": 0, 0, 101, 101\n" +
                "Image: 150, 150, 225, 225\n" +
                "    from \"tile2.png\": 0, 0, 94, 94\n" +
                "Image: 100, 300, 116, 316\n" +
                "    from \"tile1.png\": 0, 0, 101, 101\n");
    }

    @Test
    public void testRenderRepositionedImage() throws Exception {
        final SceneImage image = scene.add(new SceneImage(100, 100, 50, 50, "tile1.png"));
        image.setPosition(0, 0);
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Image: 0, 0, 50, 50\n" +
                "    from \"tile1.png\": 0, 0, 101, 101\n");
    }

    @Test
    public void testRenderClippedImage() throws Exception {
        scene.add(new SceneImage(100, 100, 50, 50, "tile1.png", 0, 0, 25, 25));
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Image: 100, 100, 150, 150\n" +
                "    from \"tile1.png\": 0, 0, 25, 25\n");
    }

    @Test
    public void testRenderReclippedClippedImage() throws Exception {
        final SceneImage image = scene.add(new SceneImage(100, 100, 50, 50,
                "tile1.png", 0, 0, 25, 25));
        image.setClipPosition(25, 25);
        assertRenderingIs("Rectangle: 0, 0, 800, 600, 0xff000000\n" +
                "Image: 100, 100, 150, 150\n" +
                "    from \"tile1.png\": 25, 25, 50, 50\n");
    }
}
