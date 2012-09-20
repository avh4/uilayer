package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.RenderTestBase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("NestedMethodCall")
public class ResourceImageTest extends RenderTestBase {
    private static final int TILE1_WIDTH = 101;
    private static final int TILE1_HEIGHT = 101;
    private ResourceImage subject;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        ResourceImage.service = Mockito.mock(UILayerService.class);
        Mockito.stub(ResourceImage.service.getImageWidth("tile1.png")).toReturn(TILE1_WIDTH);
        Mockito.stub(ResourceImage.service.getImageHeight("tile1.png")).toReturn(TILE1_HEIGHT);
        subject = new ResourceImage("tile1.png");
    }

    @Test
    public void shouldGetWidthFromTheUILayerService() throws Exception {
        assertThat(subject.getWidth(), is(TILE1_WIDTH));
    }

    @Test
    public void shouldGetHeightFromTheUiLayerSerivce() throws Exception {
        assertThat(subject.getHeight(), is(TILE1_HEIGHT));
    }

    @Test
    public void shouldDraw() throws Exception {
        subject.drawImage(g, 20, 22, 111, 111, 50, 55);
        assertRenderingIs("Image: 0, 0, 50, 55\n    from \"tile1.png\": 20, 22, 131, 133\n");
    }
}
