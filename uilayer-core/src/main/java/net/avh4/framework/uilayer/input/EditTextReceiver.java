package net.avh4.framework.uilayer.input;

public interface EditTextReceiver<M> {
    void update(M model, String text);
}
