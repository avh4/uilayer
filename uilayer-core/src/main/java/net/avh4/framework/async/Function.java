package net.avh4.framework.async;

public interface Function<PARAM, RETURN> {
    RETURN apply(PARAM param);
}
