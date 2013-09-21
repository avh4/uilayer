package net.avh4.demo.uilayer.mvc;

import net.avh4.framework.uilayer.swing.RendererWidget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlarmWidget extends RendererWidget<AlarmView> {
    public AlarmWidget(AlarmViewChannel viewModel, AlarmRenderer renderer, final AlarmCommands commands) {
        super(viewModel, renderer);
        setPreferredSize(new Dimension(800, 600));

        final JButton comp = new JButton("Click Me");
        add(comp);
        comp.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                commands.increment();
            }
        });
    }
}
