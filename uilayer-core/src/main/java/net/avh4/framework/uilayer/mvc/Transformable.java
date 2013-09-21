package net.avh4.framework.uilayer.mvc;

public interface Transformable<A, B> {
    public B transform(A data);
}
