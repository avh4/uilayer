package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.test.annotations.RequiresPreciseFontRendering;
import net.avh4.math.geometry.Rect;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static net.avh4.util.imagecomparison.ImageComparisonMatchers.isApproved;
import static org.junit.Assert.assertThat;

@SuppressWarnings({"LawOfDemeter", "NestedMethodCall", "ChainedMethodCall"})
public class SwingGraphicsOperationsTest {

    private SwingSceneRenderer subject;
    protected SwingGraphicsOperations g;
    protected Runnable graphicsOperations;

    @Rule
    public MethodRule r = new RequiresPreciseFontRendering.Rule();

    @BeforeClass
    public static void ensureAntialiasingIsOff() {
        SwingGraphicsOperations.USE_ANTIALIASING = false;
    }

    @Before
    public void setUp() {
        g = new SwingGraphicsOperations();
        final Element mockRenderer = Mockito.mock(Element.class);
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                graphicsOperations.run();
                return null;
            }
        }).when(mockRenderer).draw(Mockito.any(Rect.class),
                Mockito.same(g), Mockito.any(FontMetricsService.class));
        subject = new SwingSceneRenderer(g, mockRenderer);
    }

    protected void assertRenderingIsApproved() throws IOException {
        assertThat(subject, isApproved());
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void testRenderResourceImage() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawImage("tile1.png", 100, 100, 150, 150, 0, 0, 101, 101);
            }
        };
        assertRenderingIsApproved();
    }

    @Test
    public void testRenderMultipleImages() throws Exception {
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

    @Test
    public void testRenderClippedImage() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawImage("tile1.png", 100, 100, 150, 150, 0, 0, 25, 25);
            }
        };
        assertRenderingIsApproved();
    }

    @Test
    public void testRenderReclippedClippedImage() throws Exception {
        graphicsOperations = new Runnable() {
            @Override
            public void run() {
                g.drawImage("tile1.png", 100, 100, 150, 150, 25, 25, 50, 50);
            }
        };
        assertRenderingIsApproved();
    }

    @Test
    @RequiresPreciseFontRendering("1.7.0-u8-b04")
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

    @Test
    @RequiresPreciseFontRendering("1.7.0-u8-b04")
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

    @Test
    @RequiresPreciseFontRendering("1.7.0-u8-b04")
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

    @Test
    @RequiresPreciseFontRendering("1.7.0-u8-b04")
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

    @Test
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

    @Test
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
