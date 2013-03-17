package net.avh4.framework.uilayer.input;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.awt.event.KeyEvent;

import static org.mockito.Mockito.*;

public class EditTextTest {
    private EditText<String> subject;
    @Mock
    private EditTextReceiver<String> receiver;
    private final String model = "XYZZY";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subject = new EditText<String>(receiver);
    }

    @Test
    public void normalCharacter_shouldAppendToText() {
        subject.key(model, KeyEvent.VK_A, true);
        verify(receiver).update(model, "A");
    }

    @Test
    public void multipleCharacters_shouldAppend() {
        subject.key(model, KeyEvent.VK_A, true);
        subject.key(model, KeyEvent.VK_B, true);
        verify(receiver).update(model, "AB");
    }

    @Test
    public void space_shouldAppendToText() {
        subject.key(model, KeyEvent.VK_A, true);
        subject.key(model, KeyEvent.VK_SPACE, false);
        verify(receiver).update(model, "A ");
    }

    @Test
    public void shift_shouldBeSilent() {
        subject.key(model, KeyEvent.VK_SHIFT, false);
        verifyZeroInteractions(receiver);
    }

    @Test
    public void allAlphabeticCharacters() {
        subject.key(model, KeyEvent.VK_A, false);
        subject.key(model, KeyEvent.VK_B, false);
        subject.key(model, KeyEvent.VK_C, false);
        subject.key(model, KeyEvent.VK_D, false);
        subject.key(model, KeyEvent.VK_E, false);
        subject.key(model, KeyEvent.VK_F, false);
        subject.key(model, KeyEvent.VK_G, false);
        subject.key(model, KeyEvent.VK_H, false);
        subject.key(model, KeyEvent.VK_I, false);
        subject.key(model, KeyEvent.VK_J, false);
        subject.key(model, KeyEvent.VK_K, false);
        subject.key(model, KeyEvent.VK_L, false);
        subject.key(model, KeyEvent.VK_M, false);
        subject.key(model, KeyEvent.VK_N, false);
        subject.key(model, KeyEvent.VK_O, false);
        subject.key(model, KeyEvent.VK_P, false);
        subject.key(model, KeyEvent.VK_Q, false);
        subject.key(model, KeyEvent.VK_R, false);
        subject.key(model, KeyEvent.VK_S, false);
        subject.key(model, KeyEvent.VK_T, false);
        subject.key(model, KeyEvent.VK_U, false);
        subject.key(model, KeyEvent.VK_V, false);
        subject.key(model, KeyEvent.VK_W, false);
        subject.key(model, KeyEvent.VK_X, false);
        subject.key(model, KeyEvent.VK_Y, false);
        subject.key(model, KeyEvent.VK_Z, false);
        verify(receiver).update(model, "abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    public void numbers() {
        subject.key(model, KeyEvent.VK_1, false);
        subject.key(model, KeyEvent.VK_2, false);
        subject.key(model, KeyEvent.VK_3, false);
        subject.key(model, KeyEvent.VK_4, false);
        subject.key(model, KeyEvent.VK_5, false);
        subject.key(model, KeyEvent.VK_6, false);
        subject.key(model, KeyEvent.VK_7, false);
        subject.key(model, KeyEvent.VK_8, false);
        subject.key(model, KeyEvent.VK_9, false);
        subject.key(model, KeyEvent.VK_0, false);
        verify(receiver).update(model, "1234567890");
    }

    @Test
    public void symbols() {
        subject.key(model, KeyEvent.VK_MINUS, false);
        subject.key(model, KeyEvent.VK_PERIOD, false);
        verify(receiver).update(model, "-.");
    }

    @Test
    public void backspace_shouldDeleteLastCharacter() {
        subject.key(model, KeyEvent.VK_A, false);
        //noinspection unchecked
        reset(receiver);
        subject.key(model, KeyEvent.VK_BACK_SPACE, false);
        verify(receiver).update(model, "");
    }

    @Test
    public void backspace_withNoText() {
        subject.key(model, KeyEvent.VK_BACK_SPACE, false);
        verifyZeroInteractions(receiver);
    }
}
