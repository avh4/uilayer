package net.avh4.framework.uilayer.input;

import java.awt.event.KeyEvent;

public class EditText implements KeyReceiver {
    private final EditTextReceiver receiver;
    private String currentValue = "";

    public EditText(EditTextReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void key(int key, boolean shift) {
        String originalValue = currentValue;
        switch (key) {
            case KeyEvent.VK_BACK_SPACE:
                if (currentValue.length() > 0) {
                    currentValue = currentValue.substring(0, currentValue.length() - 1);
                }
                break;
            default:
                currentValue += getKeyText(key, shift);
        }
        if (currentValue.equals(originalValue)) return;
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
            case KeyEvent.VK_1:
                c = '1';
                break;
            case KeyEvent.VK_2:
                c = '2';
                break;
            case KeyEvent.VK_3:
                c = '3';
                break;
            case KeyEvent.VK_4:
                c = '4';
                break;
            case KeyEvent.VK_5:
                c = '5';
                break;
            case KeyEvent.VK_6:
                c = '6';
                break;
            case KeyEvent.VK_7:
                c = '7';
                break;
            case KeyEvent.VK_8:
                c = '8';
                break;
            case KeyEvent.VK_9:
                c = '9';
                break;
            case KeyEvent.VK_0:
                c = '0';
                break;
            case KeyEvent.VK_SPACE:
                c = ' ';
                break;
            case KeyEvent.VK_MINUS:
                c = '-';
                break;
            case KeyEvent.VK_PERIOD:
                c = '.';
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
