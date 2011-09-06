package net.avh4.framework.uilayer.swing.input;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.KeyReceiver;

import org.junit.Before;
import org.junit.Test;

public class SwingInputHandlerTest {

	private JPanel dummyEventSource;
	private ClickReceiver clickReceiver;
	private KeyReceiver keyReceiver;
	private SwingInputHandler subject;

	@Before
	public void setUp() {
		dummyEventSource = new JPanel();
		clickReceiver = mock(ClickReceiver.class);
		keyReceiver = mock(KeyReceiver.class);
		subject = new SwingInputHandler(clickReceiver, keyReceiver);
	}

	/**
	 * Make sure that instantiating with null receivers does not cause
	 * NullPointerExceptions.
	 */
	@Test
	public void testInstanceWithNullReceivers() {
		subject = new SwingInputHandler(null, null);
		final MouseEvent me = new MouseEvent(dummyEventSource, 0, 0, 0, 100,
				100, 1, false);
		final KeyEvent ke = new KeyEvent(dummyEventSource, 0, 0, 0,
				KeyEvent.VK_DOWN, '\0');

		subject.mouseClicked(me);
		subject.keyPressed(ke);

		// Pass
	}

	@Test
	public void testDispatchClick() {
		final MouseEvent e = new MouseEvent(dummyEventSource, 0, 0, 0, 100,
				100, 1, false);
		subject.mouseClicked(e);

		verify(clickReceiver).click(100, 100);
	}

	@Test
	public void testDispatchArrowKey() {
		final KeyEvent e = new KeyEvent(dummyEventSource, 0, 0, 0,
				KeyEvent.VK_DOWN, '\0');
		subject.keyPressed(e);

		verify(keyReceiver).key(KeyEvent.VK_DOWN);
	}
}
