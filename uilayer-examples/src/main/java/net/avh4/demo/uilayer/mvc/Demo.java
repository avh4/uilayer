package net.avh4.demo.uilayer.mvc;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

import javax.swing.*;

public class Demo {

    public static void main(String[] args) {
        String maxString = JOptionPane.showInputDialog("How many clicks shall I allow?");
        final int max = Integer.parseInt(maxString);

        MutablePicoContainer pico = new DefaultPicoContainer(new Caching());
        pico.addComponent(new AlarmConfig() {
            @Override public int max() {
                return max;
            }
        });
        pico.addComponent(AlarmWidget.class);
        pico.addComponent(AlarmRenderer.class);
        pico.addComponent(AlarmViewChannel.class);
        pico.addComponent(AlarmModelChannel.class);
        pico.addComponent(AlarmCommands.class);

        JPanel panel = pico.getComponent(AlarmWidget.class);

        JFrame window = new JFrame();
        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
