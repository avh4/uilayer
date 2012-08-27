package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.scene.SwingGraphicsOperationsTest;
import net.avh4.framework.uilayer.scene.testsuite.RenderSceneComponentTest;
import net.avh4.framework.uilayer.swing.input.SwingInputHandlerTest;
import net.avh4.framework.uilayer.swing.scene.RenderImagesTest;
import net.avh4.framework.uilayer.swing.scene.SwingImageTest;
import net.avh4.framework.uilayer.swing.scene.SwingSceneRendererTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RenderSceneComponentTest.class, SwingGraphicsOperationsTest.class,
        SwingInputHandlerTest.class, RenderImagesTest.class, SwingImageTest.class, SwingSceneRendererTest.class,
        SwingUILayerServiceTest.class})
public class AllTests {
}
