package net.avh4.framework.uilayer.mvc;

import net.avh4.framework.uilayer.mvc.Channel;

public class ConstantChannel<T> extends Channel<T> {
    private final T constantValue;

    public ConstantChannel(T constantValue) {
        super();
        this.constantValue = constantValue;
    }

    @Override public T get() {
        return constantValue;
    }
}
