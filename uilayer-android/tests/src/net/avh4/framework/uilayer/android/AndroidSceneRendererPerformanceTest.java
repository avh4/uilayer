package net.avh4.framework.uilayer.android;

import android.test.InstrumentationTestCase;
import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneText;
import org.easymock.EasyMock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class AndroidSceneRendererPerformanceTest extends InstrumentationTestCase {

    private Scene scene;
    private AndroidSceneRenderer subject;

    protected void setUp() throws Exception {
        super.setUp();
        scene = new Scene();
        final UI ui = EasyMock.createMock(UI.class);
        EasyMock.expect(ui.getScene()).andStubReturn(scene);
        EasyMock.replay(ui);
        subject = new AndroidSceneRenderer(getInstrumentation().getTargetContext(), ui);
    }

    public void testSceneTextPerformance() throws Exception {
        final String stringOfAThousandWords = makeStringOfAThousandWords();
        scene.add(new SceneText(stringOfAThousandWords, 0, 0, 720, Font.PFENNIG.size(42), Color.WHITE));

        assertThat(measureRenderingTimeUnits(), lessThan(100 * PerformanceTestCanvas.TIME_TO_DRAW_TEXT));
    }

    private int measureRenderingTimeUnits() {
        final PerformanceTestCanvas canvas = new PerformanceTestCanvas();
        subject.onDraw(canvas);
        return canvas.timeUnitsElapsed;
    }

    private String makeStringOfAThousandWords() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("Word ");
        }
        return sb.toString();
    }
}
