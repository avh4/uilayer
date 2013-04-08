package net.avh4.framework.uilayer.animation;

import net.avh4.framework.uilayer.TimeService;

public class AnimatedBoolean {
    public final int animationDurationMs;

    private final TimeService timeService = new TimeService();
    private double startValue;
    private double endValue;
    private long startTime;
    private long endTime;

    public AnimatedBoolean(int animationDurationMs) {
        this.animationDurationMs = animationDurationMs;
    }


    public void set(boolean value) {
        long now = timeService.nowMs();
        startValue = endValue;
        endValue = value ? 1. : 0.;
        startTime = now;
        endTime = now + animationDurationMs;
    }

    public double get() {
        long now = timeService.nowMs();
        if (now >= endTime) {
            return endValue;
        } else if (now <= startTime) {
            return startValue;
        } else {
            return endValue - (endValue - startValue) * (endTime - now) / (endTime - startTime);
        }
    }
}
