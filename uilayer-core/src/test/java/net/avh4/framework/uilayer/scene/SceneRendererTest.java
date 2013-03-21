package net.avh4.framework.uilayer.scene;

import net.avh4.math.Rect;
import org.junit.Before;
import org.junit.Test;

public class SceneRendererTest extends RenderTestBase {
    private Scene scene;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        scene = new Scene();
    }

    @Test
    public void testRenderEmptyScene() throws Exception {
        Rect bounds = new Rect(0, 0, this.scene.getWidth(), this.scene.getHeight());
        this.scene.draw(bounds, g, fm);
        assertRenderingIs("Rectangle: 0.0, 0.0, 800.0, 600.0, 0xff000000\n");
    }

    @Test
    public void testRenderSizedScene() throws Exception {
        scene.setSize(640, 960);
        Rect bounds = new Rect(0, 0, this.scene.getWidth(), this.scene.getHeight());
        this.scene.draw(bounds, g, fm);
        assertRenderingIs("Rectangle: 0.0, 0.0, 640.0, 960.0, 0xff000000\n");
    }

    @Test
    public void testHiddenElement() throws Exception {
        final ScenePlaceholder hidden = new ScenePlaceholder("Hidden");
        hidden.setHidden(true);
        scene.add(new Rect(100, 100, 200, 200), hidden);
        Rect bounds = new Rect(0, 0, this.scene.getWidth(), this.scene.getHeight());
        this.scene.draw(bounds, g, fm);
        assertRenderingIs("Rectangle: 0.0, 0.0, 800.0, 600.0, 0xff000000\n");
    }
}
