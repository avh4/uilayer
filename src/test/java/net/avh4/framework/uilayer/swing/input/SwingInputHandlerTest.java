package net.avh4.framework.uilayer.swing.input;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import net.avh4.framework.uilayer.ClickReceiver;

import org.junit.Test;

public class SwingInputHandlerTest {

	@Test
	public void testDispatchClick() {
		final JPanel dummyEventSource = new JPanel();
		final ClickReceiver receiver = mock(ClickReceiver.class);
		final MouseListener subject = new SwingInputHandler(receiver);
		final MouseEvent e = new MouseEvent(dummyEventSource, 0, 0, 0, 100,
				100, 1, false);
		subject.mouseClicked(e);

		verify(receiver).click(100, 100);
	}

}
