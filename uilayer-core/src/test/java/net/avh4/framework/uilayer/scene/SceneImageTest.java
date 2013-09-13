package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Image;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.stub;

@SuppressWarnings({"LawOfDemeter", "NestedMethodCall", "ChainedMethodCall"})
public class SceneImageTest extends RenderTestBase {

    private static final int IMAGE_WIDTH = 101;
    private static final int IMAGE_HEIGHT = 101;
    @Mock
    private Image image;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(image.getWidth()).toReturn(IMAGE_WIDTH);
        stub(image.getHeight()).toReturn(IMAGE_HEIGHT);
    }

    @Test
    public void shouldSetInitialClipToImageSize() throws Exception {
        SceneImage tile1 = new SceneImage(image);
        assertThat(tile1.clipX, is(0));
        assertThat(tile1.clipY, is(0));
        assertThat(tile1.clipWidth, is(IMAGE_WIDTH));
        assertThat(tile1.clipHeight, is(IMAGE_HEIGHT));
    }

    @Test
    public void shouldDrawImage() throws Exception {
        draw(Rect.fromTopLeft(100, 120, 50, 50), new SceneImage(image));
        assertRenderingIs("" +
                "Image: 100.0, 120.0, 150.0, 170.0\n    from \"image\": 0, 0, 101, 101\n");
    }

    @Test
    public void testRenderClippedImage() throws Exception {
        draw(Rect.fromTopLeft(0, 0, 50, 55), new SceneImage(image, 0, 1, 20, 22));
        assertRenderingIs("" +
                "Image: 0.0, 0.0, 50.0, 55.0\n    from \"image\": 0, 1, 20, 23\n");
    }

    @Test
    public void testRenderReclippedClippedImage() throws Exception {
        final SceneImage image = new SceneImage(this.image, 0, 0, 20, 22);
        image.setClipPosition(10, 11);
        draw(Rect.fromTopLeft(0, 0, 50, 55), image);
        assertRenderingIs("" +
                "Image: 0.0, 0.0, 50.0, 55.0\n    from \"image\": 10, 11, 30, 33\n");
    }

    @Test
    public void withNoImage_shouldRenderAPlaceholder() throws Exception {
        draw(Rect.fromTopLeft(0, 0, 100, 200), new SceneImage());
        assertRenderingIs("" +
                "Rectangle: 0.0, 0.0, 100.0, 200.0, 0xffb7abfb\n" +
                "Text: \"Missing Image\" 5.0, 195.0 Font{'Pfennig.ttf' (12)} 0xff000000\n");
    }

    @Test
    public void setImage_shouldSetTheImageAndResetClipSize() throws Exception {
        SceneImage image = new SceneImage();
        image.setImage(this.image);
        draw(Rect.fromTopLeft(10, 11, 100, 200), image);
        assertRenderingIs("" +
                "Image: 10.0, 11.0, 110.0, 211.0\n    from \"image\": 0, 0, 101, 101\n");
    }

    @Test
    public void setImage_shouldResetClipPosition() throws Exception {
        SceneImage image = new SceneImage();
        image.setClipPosition(70, 77);
        image.setImage(this.image);
        draw(Rect.fromTopLeft(10, 11, 100, 200), image);
        assertRenderingIs("" +
                "Image: 10.0, 11.0, 110.0, 211.0\n    from \"image\": 0, 0, 101, 101\n");
    }
}
