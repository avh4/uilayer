package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.ResponseListener;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.test.annotations.SkipIfHeadless;
import net.avh4.test.junit.Nested;
import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.finder.JOptionPaneFinder;
import org.fest.swing.fixture.JOptionPaneFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.MethodRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


@SuppressWarnings({"NestedMethodCall", "ChainedMethodCall"})
@RunWith(Nested.class)
public class SwingUILayerServiceTest {
    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    private UILayerService service;

    @Before
    public void setUp() throws Exception {
        service = new SwingUILayerService();
    }

    @Test
    public void testGetImageWidth() throws Exception {
        assertThat(service.getImageWidth("tile1.png"), is(101));
    }

    @Test
    public void testGetImageHeight() throws Exception {
        assertThat(service.getImageHeight("tile1.png"), is(101));
    }

    @Test
    public void testGetPixel() throws Exception {
        assertThat(service.getPixel("tile1.png", 11, 12), is(0xaa0023ae));
    }

    @Test
    public void testGetFontHeight() throws Exception {
        assertThat(service.getFontHeight(Font.PFENNIG.size(16)), is(22));
    }

    @Test
    public void testMeasureText() throws Exception {
        assertThat(service.measureText(Font.PFENNIG.size(16), "quick"), is(36));
    }

    @SuppressWarnings("UnusedDeclaration")
    public class ShowChoices {
        @Rule
        public MethodRule skipIfHeadless = new SkipIfHeadless.Rule();

        private JOptionPaneFixture dialog;
        private Robot robot;
        @Mock
        private ResponseListener listener;

        @Before
        public void setup() throws Exception {
            MockitoAnnotations.initMocks(this);
            robot = BasicRobot.robotWithCurrentAwtHierarchy();
            service.showChoices("Title", Arrays.asList("Item 1", "Item 2", "Item 3"), listener);
            dialog = JOptionPaneFinder.findOptionPane().using(robot);
        }

        @After
        public void tearDown() {
            robot.cleanUp();
        }

        @Test
        @SkipIfHeadless
        public void shouldShowADialog() throws Exception {
            assertThat(dialog, notNullValue());
        }

        @Test
        @SkipIfHeadless
        public void shouldShowTitle() throws Exception {
            dialog.requireTitle("Title");
        }

        @Test
        @SkipIfHeadless
        public void shouldShowChoices() throws Exception {
            dialog.comboBox().requireItemCount(3);
            assertThat(dialog.comboBox().valueAt(0), is("Item 1"));
            assertThat(dialog.comboBox().valueAt(1), is("Item 2"));
            assertThat(dialog.comboBox().valueAt(2), is("Item 3"));
        }

        @Test
        @SkipIfHeadless
        public void shouldCallListenerWhenAChoiceIsMade() throws Exception {
            dialog.comboBox().selectItem("Item 2");
            dialog.okButton().click();
            Mockito.verify(listener).response("Item 2");
        }
    }
}
