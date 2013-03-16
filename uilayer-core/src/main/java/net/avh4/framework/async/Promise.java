package net.avh4.framework.async;

public interface Promise<T> {
    T waitForValue() throws InterruptedException;

    void whenDone(Function<T, ?> callable);
}
