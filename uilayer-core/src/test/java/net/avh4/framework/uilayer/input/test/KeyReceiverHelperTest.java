package net.avh4.framework.uilayer.input.test;

import net.avh4.framework.uilayer.input.KeyReceiver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.verify;

public class KeyReceiverHelperTest {
    @Mock
    KeyReceiver receiver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void type_shouldSendSingleCharacter() {
        KeyReceiverHelper.type(receiver, "a");
        verify(receiver).key(KeyEvent.VK_A, false);
    }

    @Test
    public void type_shouldSendCapitalLetterWithShift() {
        KeyReceiverHelper.type(receiver, "A");
        verify(receiver).key(KeyEvent.VK_A, true);
    }

    @Test
    public void type_shouldSendMultipleCharacters() {
        KeyReceiverHelper.type(receiver, "Hi");
        verify(receiver).key(KeyEvent.VK_H, true);
        verify(receiver).key(KeyEvent.VK_I, false);
    }

    @Test
    public void allAlphabeticCharacters() {
        KeyReceiverHelper.type(receiver, "abcdefghijklmnopqrstuvwxyz");
        verify(receiver).key(KeyEvent.VK_A, false);
        verify(receiver).key(KeyEvent.VK_B, false);
        verify(receiver).key(KeyEvent.VK_C, false);
        verify(receiver).key(KeyEvent.VK_D, false);
        verify(receiver).key(KeyEvent.VK_E, false);
        verify(receiver).key(KeyEvent.VK_F, false);
        verify(receiver).key(KeyEvent.VK_G, false);
        verify(receiver).key(KeyEvent.VK_H, false);
        verify(receiver).key(KeyEvent.VK_I, false);
        verify(receiver).key(KeyEvent.VK_J, false);
        verify(receiver).key(KeyEvent.VK_K, false);
        verify(receiver).key(KeyEvent.VK_L, false);
        verify(receiver).key(KeyEvent.VK_M, false);
        verify(receiver).key(KeyEvent.VK_N, false);
        verify(receiver).key(KeyEvent.VK_O, false);
        verify(receiver).key(KeyEvent.VK_P, false);
        verify(receiver).key(KeyEvent.VK_Q, false);
        verify(receiver).key(KeyEvent.VK_R, false);
        verify(receiver).key(KeyEvent.VK_S, false);
        verify(receiver).key(KeyEvent.VK_T, false);
        verify(receiver).key(KeyEvent.VK_U, false);
        verify(receiver).key(KeyEvent.VK_V, false);
        verify(receiver).key(KeyEvent.VK_W, false);
        verify(receiver).key(KeyEvent.VK_X, false);
        verify(receiver).key(KeyEvent.VK_Y, false);
        verify(receiver).key(KeyEvent.VK_Z, false);
    }

    @Test
    public void numbers() {
        KeyReceiverHelper.type(receiver, "1234567890");
        verify(receiver).key(KeyEvent.VK_1, false);
        verify(receiver).key(KeyEvent.VK_2, false);
        verify(receiver).key(KeyEvent.VK_3, false);
        verify(receiver).key(KeyEvent.VK_4, false);
        verify(receiver).key(KeyEvent.VK_5, false);
        verify(receiver).key(KeyEvent.VK_6, false);
        verify(receiver).key(KeyEvent.VK_7, false);
        verify(receiver).key(KeyEvent.VK_8, false);
        verify(receiver).key(KeyEvent.VK_9, false);
        verify(receiver).key(KeyEvent.VK_0, false);
    }

    @Test
    public void symbols() {
        KeyReceiverHelper.type(receiver, "-.");
        verify(receiver).key(KeyEvent.VK_MINUS, false);
        verify(receiver).key(KeyEvent.VK_PERIOD, false);
    }
}
