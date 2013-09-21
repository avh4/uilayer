package net.avh4.framework.uilayer.mvc;

public class LastValue<T> {
    private T lastValue;

    public LastValue(final Channel<T> input) {
        input.watch(new Observer() {
            @Override public void update() {
                lastValue = input.get();
            }
        });
    }

    public T get() {
        return lastValue;
    }
}
