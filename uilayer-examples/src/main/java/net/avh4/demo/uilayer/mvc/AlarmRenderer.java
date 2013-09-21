package net.avh4.demo.uilayer.mvc;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.framework.uilayer.mvc.Renderer;

public class AlarmRenderer implements Renderer<AlarmView> {
    @Override public void draw(AlarmView data, Rect rect, GraphicsOperations go, FontMetricsService fm) {
        go.drawRect(rect, Color.BLACK);
        go.drawRect(rect.inset(5), data.color());
        Rect barArea = rect.inset(5).left(120).inset(20);
        go.drawRect(barArea, Color.BLACK);
        go.drawRect(barArea.inset(10).bottomPercent(data.percent()), Color.RED);

        if (data.showDanger()) {
            go.drawText(fm, "DANGER!", rect.right(rect.width() - barArea.maxX() - 30), Font.OPEN_SANS.size(80), Color.RED);
        }
    }
}
