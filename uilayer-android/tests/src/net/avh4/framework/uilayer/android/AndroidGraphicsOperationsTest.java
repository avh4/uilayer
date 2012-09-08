package net.avh4.framework.uilayer.android;

import android.test.ActivityInstrumentationTestCase2;
import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.SceneRenderer;
import net.avh4.util.imagecomparison.android.Matchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

@SuppressWarnings({"LawOfDemeter", "NestedMethodCall", "ChainedMethodCall"})
public class AndroidGraphicsOperationsTest extends ActivityInstrumentationTestCase2<AndroidSceneRendererActivity>  {

    protected AndroidGraphicsOperations g;
    private AndroidSceneRenderer subject;
    protected Runnable graphicsOperations;

    public AndroidGraphicsOperationsTest() {
        super(AndroidSceneRendererActivity.class);
    }

    @Override
    public void setUp() {
        g = new AndroidGraphicsOperations(getInstrumentation().getContext());
        final SceneRenderer mockRenderer = Mockito.mock(SceneRenderer.class);
        Mockito.stub(mockRenderer.getWidth()).toReturn(800);
        Mockito.stub(mockRenderer.getHeight()).toReturn(600);
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                graphicsOperations.run();
                return null;
            }
        }).when(mockRenderer).render(Mockito.same(g), Mockito.any(FontMetricsService.class));

        subject = new AndroidSceneRenderer(getInstrumentation().getContext(), g, mockRenderer);
        getActivity().setRenderer(subject);
    }

    protected void assertRenderingIsApproved() throws IOException {
        MatcherAssert.assertThat(getActivity(), isApproved());
    }

    private Matcher<? super AndroidSceneRendererActivity> isApproved() {
        return Matchers.isApproved(getInstrumentation().getContext());
    }

    public void testRenderLine() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawLine(100, 100, 200, 200, Color.WHITE);
                g.drawLine(0, 300, 800, 0, Color.YELLOW);
            }
        };
        assertRenderingIsApproved();
    }

    public void testRenderRect() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawRect(50, 50, 200, 500, Color.RED);
                g.drawRect(450, 50, 200, 300, Color.YELLOW);
            }
        };
        assertRenderingIsApproved();
    }

    public void testRenderOval() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawOval(50, 50, 200, 500, Color.RED);
                g.drawOval(450, 50, 200, 300, Color.YELLOW);
            }
        };
        assertRenderingIsApproved();
    }

    public void xtestRenderResourceImage() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawImage("tile1.png", 100, 100, 150, 150, 0, 0, 101, 101);
            }
        };
        assertRenderingIsApproved();
    }

    public void xtestRenderMultipleImages() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawImage("tile1.png", 100, 100, 250, 250, 0, 0, 101, 101);
                g.drawImage("tile2.png", 150, 150, 225, 225, 0, 0, 94, 94);
                g.drawImage("tile1.png", 100, 300, 116, 316, 0, 0, 101, 101);
            }
        };
        assertRenderingIsApproved();
    }

    public void xtestRenderClippedImage() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawImage("tile1.png", 100, 100, 150, 150, 0, 0, 25, 25);
            }
        };
        assertRenderingIsApproved();
    }

    public void xtestRenderReclippedClippedImage() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawImage("tile1.png", 100, 100, 150, 150, 25, 25, 50, 50);
            }
        };
        assertRenderingIsApproved();
    }

    public void testRenderCenteredText() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawRect(100, 50, 300, 150, Color.GREY);
                g.drawText("CENTER", 134.5f, 150.5f, Font.PFENNIG.size(64), Color.WHITE);
            }
        };
        assertRenderingIsApproved();
    }

    public void testBaselinesShouldBeAligned() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawRect(100, 50, 75, 150, Color.darken(.5, Color.BLUE));
                g.drawText("A", 117.0f, 150.5f, Font.PFENNIG.size(64), Color.BLUE);
                g.drawRect(175, 50, 75, 150, Color.darken(.5, Color.GREEN));
                g.drawText("a", 197.5f, 150.5f, Font.PFENNIG.size(64), Color.GREEN);
            }
        };
        assertRenderingIsApproved();
    }

    public void testRenderLabel() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawRect(400, 300, 1, 1, Color.RED);
                g.drawText("Red Point", 376.5f, 317.0f, new Font("Tuffy.ttf", 12), Color.YELLOW);
            }
        };
        assertRenderingIsApproved();
    }

    public void testIncludedFontPfennig() throws Exception {
        final Font font = Font.PFENNIG.size(32);
        final String text = "Pfennig";
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawRect(100, 100, font.measureText(text), font.getLineHeight(), Color.GREY);
                g.drawText(text, 100, 134, font, Color.YELLOW);
            }
        };
        assertRenderingIsApproved();
    }

    public void testTranslation() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawRect(10, 10, 10, 10, Color.YELLOW);
                g.translate(300, 400);
                g.drawRect(0, 0, 100, 100, Color.RED);
                g.drawRect(5, 5, 90, 90, Color.WHITE);
                g.drawOval(20, 20, 60, 60, Color.BLUE);
                g.translate(-300, -400);
                g.drawRect(20, 20, 10, 10, Color.RED);
            }
        };
        assertRenderingIsApproved();
    }

    public void testDrawingTransparentColor() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawRect(0, 0, 256 * 2, 40, Color.YELLOW);
                for (int i = 0; i < 256; i++) {
                    g.drawRect(i * 2, 10, 2, 80, Color.alpha(Color.RED, i));
                }
            }
        };
        assertRenderingIsApproved();
    }
}
