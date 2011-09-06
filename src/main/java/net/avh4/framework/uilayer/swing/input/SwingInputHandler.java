package net.avh4.framework.uilayer.swing.input;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import net.avh4.framework.uilayer.ClickReceiver;

public class SwingInputHandler implements MouseListener {

	private final ClickReceiver receiver;
	private final Component componentToRepaint;

	public SwingInputHandler(final ClickReceiver receiver) {
		this(receiver, null);
	}

	/**
	 * This constructor allows a UI component to be specified which will be told
	 * to repaint itself after each event is dispatched.
	 */
	public SwingInputHandler(final ClickReceiver receiver,
			final Component componentToRepaint) {
		this.receiver = receiver;
		this.componentToRepaint = componentToRepaint;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		receiver.click(e.getX(), e.getY());
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

}
