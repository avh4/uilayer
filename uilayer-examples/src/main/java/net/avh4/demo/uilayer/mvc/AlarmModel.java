package net.avh4.demo.uilayer.mvc;

class AlarmModel {
    private final int count;
    private final int max;

    public AlarmModel(int max) {
        count = 0;
        this.max = max;
    }

    protected AlarmModel(int count, int max) {
        this.count = count;
        this.max = max;
    }

    public AlarmModel increment() {
        if (count >= max) return this;
        return new AlarmModel(count + 1, max);
    }

    public int count() {
        return count;
    }

    public int max() {
        return max;
    }
}
