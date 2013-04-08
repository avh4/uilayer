package net.avh4.framework.uilayer;

import java.util.Date;

public class TimeService {
    public long nowMs() {
        return new Date().getTime();
    }
}
