package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.mvc.Channel;
import net.avh4.framework.uilayer.mvc.Observer;
import net.avh4.framework.uilayer.swing.SwingFontMetricsService;
import net.avh4.framework.uilayer.swing.SwingGraphicsOperations;
import net.avh4.math.geometry.Rect;

import javax.swing.*;
import java.awt.*;

public class RendererWidget<VM> extends JPanel implements Observer {

    private final Channel<VM> model;
    private final net.avh4.framework.uilayer.mvc.Renderer<VM> renderer;
    private final SwingGraphicsOperations go;
    private final SwingFontMetricsService fm;

    public RendererWidget(Channel<VM> model, net.avh4.framework.uilayer.mvc.Renderer<VM> renderer) {
        this.model = model;
        this.renderer = renderer;
        model.watch(this);
        go = new SwingGraphicsOperations();
        fm = new SwingFontMetricsService();
    }

    @Override protected void paintComponent(Graphics g) {
        final Rect rect = Rect.ofSize(getWidth(), getHeight());
        go.setGraphicsContext(g);
        fm.setGraphicsContext(g);
        final VM data = model.get();
        renderer.draw(data, rect, go, fm);
        fm.setGraphicsContext(null);
        go.setGraphicsContext(null);
    }

    @Override public void update() {
        repaint();
    }
}
