package net.avh4.framework.uilayer.mvc;

public class ChannelTransformation<A, B extends Transformable<A, B>> extends Channel<B> implements Observer {
    private final Channel<A> input;
    private B value;

    public ChannelTransformation(Channel<A> input, B initialValue) {
        this.input = input;
        this.value = initialValue;
        input.watch(this);
    }

    @Override public B get() {
        return this.value;
    }

    @Override public void update() {
        synchronized (this) {
            final A data = input.get();
            this.value = this.value.transform(data);
        }
        notifyObservers();
    }
}
