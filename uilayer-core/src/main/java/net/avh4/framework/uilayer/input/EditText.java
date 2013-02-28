package net.avh4.framework.uilayer.input;

import net.avh4.framework.uilayer.KeyReceiver;

import java.awt.event.KeyEvent;

public class EditText implements KeyReceiver {
    private final EditTextReceiver receiver;
    private String currentValue = "";

    public EditText(EditTextReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void key(int key, boolean shift) {
        switch (key) {
            case KeyEvent.VK_SPACE:
                currentValue += " ";
                break;
            default:
                currentValue += getKeyText(key, shift);
        }
        receiver.update(currentValue);
    }

    private String getKeyText(int key, boolean shift) {
        char c = 0x00;
        switch (key) {
            case KeyEvent.VK_A:
                c = 'a';
                break;
            case KeyEvent.VK_B:
                c = 'b';
                break;
            case KeyEvent.VK_C:
                c = 'c';
                break;
            case KeyEvent.VK_D:
                c = 'd';
                break;
            case KeyEvent.VK_E:
                c = 'e';
                break;
            case KeyEvent.VK_F:
                c = 'f';
                break;
            case KeyEvent.VK_G:
                c = 'g';
                break;
            case KeyEvent.VK_H:
                c = 'h';
                break;
            case KeyEvent.VK_I:
                c = 'i';
                break;
            case KeyEvent.VK_J:
                c = 'j';
                break;
            case KeyEvent.VK_K:
                c = 'k';
                break;
            case KeyEvent.VK_L:
                c = 'l';
                break;
            case KeyEvent.VK_M:
                c = 'm';
                break;
            case KeyEvent.VK_N:
                c = 'n';
                break;
            case KeyEvent.VK_O:
                c = 'o';
                break;
            case KeyEvent.VK_P:
                c = 'p';
                break;
            case KeyEvent.VK_Q:
                c = 'q';
                break;
            case KeyEvent.VK_R:
                c = 'r';
                break;
            case KeyEvent.VK_S:
                c = 's';
                break;
            case KeyEvent.VK_T:
                c = 't';
                break;
            case KeyEvent.VK_U:
                c = 'u';
                break;
            case KeyEvent.VK_V:
                c = 'v';
                break;
            case KeyEvent.VK_W:
                c = 'w';
                break;
            case KeyEvent.VK_X:
                c = 'x';
                break;
            case KeyEvent.VK_Y:
                c = 'y';
                break;
            case KeyEvent.VK_Z:
                c = 'z';
                break;
        }

        if (c == 0x00) {
            return "";
        } else if (shift) {
            return "" + Character.toUpperCase(c);
        } else {
            return "" + c;
        }
    }
}
