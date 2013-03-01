package net.avh4.framework.uilayer.input;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

public class EditTextTest {
    private EditText subject;
    @Mock
    private EditTextReceiver receiver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new EditText(receiver);
    }

    @Test
    public void normalCharacter_shouldAppendToText() {
        subject.key(KeyEvent.VK_A, true);
        verify(receiver).update("A");
    }

    @Test
    public void multipleCharacters_shouldAppend() {
        subject.key(KeyEvent.VK_A, true);
        subject.key(KeyEvent.VK_B, true);
        verify(receiver).update("AB");
    }

    @Test
    public void space_shouldAppendToText() {
        subject.key(KeyEvent.VK_A, true);
        subject.key(KeyEvent.VK_SPACE, false);
        verify(receiver).update("A ");
    }

    @Test
    public void shift_shouldBeSilent() {
        subject.key(KeyEvent.VK_SHIFT, false);
        verifyZeroInteractions(receiver);
    }

    @Test
    public void allAlphabeticCharacters() {
        subject.key(KeyEvent.VK_A, false);
        subject.key(KeyEvent.VK_B, false);
        subject.key(KeyEvent.VK_C, false);
        subject.key(KeyEvent.VK_D, false);
        subject.key(KeyEvent.VK_E, false);
        subject.key(KeyEvent.VK_F, false);
        subject.key(KeyEvent.VK_G, false);
        subject.key(KeyEvent.VK_H, false);
        subject.key(KeyEvent.VK_I, false);
        subject.key(KeyEvent.VK_J, false);
        subject.key(KeyEvent.VK_K, false);
        subject.key(KeyEvent.VK_L, false);
        subject.key(KeyEvent.VK_M, false);
        subject.key(KeyEvent.VK_N, false);
        subject.key(KeyEvent.VK_O, false);
        subject.key(KeyEvent.VK_P, false);
        subject.key(KeyEvent.VK_Q, false);
        subject.key(KeyEvent.VK_R, false);
        subject.key(KeyEvent.VK_S, false);
        subject.key(KeyEvent.VK_T, false);
        subject.key(KeyEvent.VK_U, false);
        subject.key(KeyEvent.VK_V, false);
        subject.key(KeyEvent.VK_W, false);
        subject.key(KeyEvent.VK_X, false);
        subject.key(KeyEvent.VK_Y, false);
        subject.key(KeyEvent.VK_Z, false);
        verify(receiver).update("abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    public void numbers() {
        subject.key(KeyEvent.VK_1, false);
        subject.key(KeyEvent.VK_2, false);
        subject.key(KeyEvent.VK_3, false);
        subject.key(KeyEvent.VK_4, false);
        subject.key(KeyEvent.VK_5, false);
        subject.key(KeyEvent.VK_6, false);
        subject.key(KeyEvent.VK_7, false);
        subject.key(KeyEvent.VK_8, false);
        subject.key(KeyEvent.VK_9, false);
        subject.key(KeyEvent.VK_0, false);
        verify(receiver).update("1234567890");
    }

    @Test
    public void symbols() {
        subject.key(KeyEvent.VK_MINUS, false);
        verify(receiver).update("-");
    }

    @Test
    public void backspace_shouldDeleteLastCharacter() {
        subject.key(KeyEvent.VK_A, false);
        reset(receiver);
        subject.key(KeyEvent.VK_BACK_SPACE, false);
        verify(receiver).update("");
    }

    @Test
    public void backspace_withNoText() {
        subject.key(KeyEvent.VK_BACK_SPACE, false);
        verifyZeroInteractions(receiver);
    }
}
