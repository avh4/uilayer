package net.avh4.framework.async;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

public class DeferredTest {
    @Rule
    public Timeout globalTimeout = new Timeout(1000);

    private Deferred<String> subject;
    @Mock
    Function<String, Void> callback;
    private boolean gotValue;
    private Thread waitForValue;
    private String returnedValue;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new Deferred<String>();

        waitForValue = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    returnedValue = subject.waitForValue();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                gotValue = true;
            }
        });
    }

    @Test
    public void waitForValue_afterResolve_returnsResolvedValue() throws Exception {
        subject.resolve("DONE!");
        assertThat(subject.waitForValue(), is("DONE!"));
    }

    @Test
    public void waitForValue_beforeResolve_doesNotReturn() throws Exception {
        waitForValue.start();
        waitForValue.join(10);
        assertThat(gotValue, is(false));
    }

    @Test
    public void waitForValue_beforeResolve_returnsWhenResolved() throws Exception {
        waitForValue.start();
        waitForValue.join(10);
        subject.resolve("DONE!");
        waitForValue.join();
        assertThat(gotValue, is(true));
        assertThat(returnedValue, is("DONE!"));
    }

    @Test
    public void waitForValue_afterResolve_canBeCalledManyTimes() throws Exception {
        subject.resolve("DONE!");
        subject.waitForValue();
        assertThat(subject.waitForValue(), is("DONE!"));
    }

    @Test
    public void whenDone_afterResolve_getsExecuted() {
        subject.resolve("DONE!");
        subject.whenDone(callback);
        verify(callback).apply("DONE!");
    }

    @Test
    public void whenDone_beforeResolve_getsExecuted() {
        subject.whenDone(callback);
        subject.resolve("DONE!");
        verify(callback).apply("DONE!");
    }

    @Test
    public void resolveWithNull_returnsResolvedValue() throws Exception {
        subject.resolve(null);
        assertThat(subject.waitForValue(), nullValue());
    }

    @Test
    public void resolveWithNull_executesWhenDoneCallback() {
        subject.resolve(null);
        subject.whenDone(callback);
        verify(callback).apply(null);
    }
}
