package net.avh4.demo.uilayer.mvc;

import net.avh4.framework.uilayer.Color;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.stub;

public class AlarmViewTest {

    private AlarmView subject;
    @Mock private AlarmModel model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(model.max()).toReturn(10);
        stub(model.count()).toReturn(3);
        subject = new AlarmView(100, 0);
    }

    @Test
    public void testPercent() throws Exception {
        subject = subject.transform(model);
        assertThat(subject.percent(), is(0.3));
    }

    @Test
    public void showDanger_below90_shouldBeFalse() throws Exception {
        subject = subject.transform(model);
        assertThat(subject.showDanger(), is(false));
    }

    @Test
    public void showDanger_at90_shouldBeTrue() throws Exception {
        stub(model.count()).toReturn(9);
        subject = subject.transform(model);
        assertThat(subject.showDanger(), is(true));
    }

    @Test
    public void color_at0_shouldBeStartColor() throws Exception {
        stub(model.count()).toReturn(0);
        subject = subject.transform(model);
        assertThat(subject.color(), is(Color.fromHSL(100, 0.5, 0.5)));
    }

    @Test
    public void color_at100_shouldBeEndColor() throws Exception {
        stub(model.count()).toReturn(10);
        subject = subject.transform(model);
        assertThat(subject.color(), is(Color.fromHSL(0, 0.5, 0.5)));
    }
}
