package net.avh4.framework.uilayer;

public interface TimerUpdate {
    public static final UpdateAction NO_UPDATE = UpdateAction.NO_UPDATE;
    public static final UpdateAction NEEDS_REDRAW = UpdateAction.NEEDS_REDRAW;

    enum UpdateAction {NO_UPDATE, NEEDS_REDRAW}

    UpdateAction time();
}
