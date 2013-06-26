package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.input.ClickReceiver;
import net.avh4.framework.uilayer.input.KeyReceiver;

public interface UI extends Element, ClickReceiver, KeyReceiver, TimerUpdate {
}
