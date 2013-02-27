package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.KeyReceiver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingInputHandler implements MouseListener, KeyListener {

    private final ClickReceiver clickReceiver;
    private final KeyReceiver keyReceiver;
    private final Component componentToRepaint;

    public SwingInputHandler(final ClickReceiver receiver,
                             final KeyReceiver keyReceiver) {
        this(receiver, keyReceiver, null);
    }

    /**
     * This constructor allows a UI component to be specified which will be told
     * to repaint itself after each event is dispatched.
     */
    public SwingInputHandler(final ClickReceiver clickReceiver,
                             final KeyReceiver keyReceiver, final Component componentToRepaint) {
        this.clickReceiver = clickReceiver;
        this.keyReceiver = keyReceiver;
        this.componentToRepaint = componentToRepaint;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (clickReceiver == null) {
            return;
        }
        clickReceiver.click(e.getX(), e.getY());
        repaint();
    }

    private void repaint() {
        if (componentToRepaint != null) {
            componentToRepaint.repaint();
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        if (keyReceiver == null) {
            return;
        }
        boolean shift = e.isShiftDown();
        int keyCode = e.getKeyCode();
        keyReceiver.key(keyCode, shift);
        repaint();
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }
}
