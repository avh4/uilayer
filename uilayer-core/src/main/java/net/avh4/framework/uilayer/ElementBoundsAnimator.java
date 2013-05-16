package net.avh4.framework.uilayer;

import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;

import java.util.ArrayList;
import java.util.HashMap;

public class ElementBoundsAnimator {
    public static final long ANIMATION_DURATION_MS = 500;

    private final TimeService timeService;
    private final ArrayList<Element> visibleThings = new ArrayList<Element>();
    private final HashMap<Object, Rect> startBounds = new HashMap<Object, Rect>();
    private final HashMap<Object, Rect> endBounds = new HashMap<Object, Rect>();
    private final HashMap<Object, Long> startTime = new HashMap<Object, Long>();
    private final HashMap<Object, Long> endTime = new HashMap<Object, Long>();

    public ElementBoundsAnimator(TimeService timeService) {
        this.timeService = timeService;
    }

    public void updateElement(Rect bounds, Element element) {
        long now = timeService.nowMs();

        if (visibleThings.contains(element)) {
            if (bounds.equals(endBounds.get(element))) return; // TODO: test
            startBounds.put(element, getBounds(now, element));
            endBounds.put(element, bounds);
            startTime.put(element, now);
            endTime.put(element, now + ANIMATION_DURATION_MS);
            return;
        }
        visibleThings.remove(element);
        visibleThings.add(element);
        this.endBounds.put(element, bounds);
        this.endTime.put(element, now);
    }

    public void render(GraphicsOperations g, FontMetricsService fm) {
        long now = timeService.nowMs();

        for (Element element : visibleThings) {
            Rect bounds = getBounds(now, element);

            element.draw(bounds, g, fm);
        }
    }

    private Rect getBounds(long now, Object id) {
        Rect startBounds = this.startBounds.get(id);
        Rect endBounds = this.endBounds.get(id);

        long endTime = this.endTime.get(id);
        if (now >= endTime) {
            return endBounds;
        }
        long startTime = this.startTime.get(id);
        if (now <= startTime) {
            return startBounds;
        }

        double percent = (now - startTime) / (double) (endTime - startTime);
        return Rect.interpolate(startBounds, endBounds, percent);
    }
}
