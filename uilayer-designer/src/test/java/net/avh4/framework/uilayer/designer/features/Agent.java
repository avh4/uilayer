package net.avh4.framework.uilayer.designer.features;

import net.avh4.framework.uilayer.designer.Designer;

class Agent {
    private final Designer designer;

    public Agent(Designer designer) {
        this.designer = designer;
    }

    void addPlaceholder(int x1, int y1, int x2, int y2) {
        designer.dragStart(x1, y1);
        designer.dragEnd(x2, y2);
    }

    public void saveMockup(String filename) {
        designer.chooseMenuItem("Save");
    }
}
