package net.avh4.demo.uilayer.mvc;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.mvc.Transformable;

class AlarmView implements Transformable<AlarmModel, AlarmView> {
    private final int startHue;
    private final int endHue;
    private final AlarmModel model;

    public AlarmView(int startHue, int endHue) {
        this(startHue, endHue, null);
    }

    protected AlarmView(int startHue, int endHue, AlarmModel model) {
        this.startHue = startHue;
        this.endHue = endHue;
        this.model = model;
    }

    @Override public AlarmView transform(AlarmModel value) {
        return new AlarmView(startHue, endHue, value);
    }

    public int color() {
        return Color.fromHSL(startHue + percent() * (endHue - startHue), 0.5, 0.5);
    }

    public double percent() {
        return (double) model.count() / model.max();
    }

    public boolean showDanger() {
        return percent() >= 0.9;
    }
}
