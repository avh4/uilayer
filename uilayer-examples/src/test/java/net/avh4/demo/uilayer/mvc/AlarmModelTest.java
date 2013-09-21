package net.avh4.demo.uilayer.mvc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AlarmModelTest {

    private AlarmModel subject;

    @Before
    public void setUp() throws Exception {
        subject = new AlarmModel(3);
    }

    @Test
    public void shouldHaveMax() throws Exception {
        assertThat(subject.max(), is(3));
    }

    @Test
    public void count_shouldStartAtZero() throws Exception {
        assertThat(subject.count(), is(0));
    }

    @Test
    public void increment_shouldIncreaseCount() throws Exception {
        assertThat(subject.increment().count(), is(1));
    }

    @Test
    public void increment_atMax_shouldKeepCount() throws Exception {
        subject = subject.increment().increment().increment();
        assertThat(subject.count(), is(3));
        assertThat(subject.increment().count(), is(3));
    }
}
