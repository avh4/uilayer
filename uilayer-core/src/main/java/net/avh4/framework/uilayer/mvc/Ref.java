package net.avh4.framework.uilayer.mvc;

public class Ref<T> extends Channel<T> {
    private T value;

    public Ref(T initialValue) {
        value = initialValue;
    }

    public void swap(T old, T newValue) {
        synchronized (this) {
            if (value != old && !value.equals(old)) {
                throw new IllegalArgumentException("Ref swap is invalid");
            }
            value = newValue;
        }
        notifyObservers();
    }

    @Override public T get() {
        return value;
    }
}
