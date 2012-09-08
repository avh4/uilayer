package net.avh4.framework.uilayer.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

class PerformanceTestCanvas extends Canvas {
    public int timeUnitsElapsed;
    private static final int TIME_TO_DRAW_RECT = 1;
    public static final int TIME_TO_DRAW_TEXT = 1;

    @Override
    public void drawRect(RectF rect, Paint paint) {
        super.drawRect(rect, paint);
        timeUnitsElapsed += TIME_TO_DRAW_RECT;
    }

    @Override
    public void drawRect(Rect r, Paint paint) {
        super.drawRect(r, paint);
        timeUnitsElapsed += TIME_TO_DRAW_RECT;
    }

    @Override
    public void drawRect(float left, float top, float right, float bottom, Paint paint) {
        super.drawRect(left, top, right, bottom, paint);
        timeUnitsElapsed += TIME_TO_DRAW_RECT;
    }

    @Override
    public void drawText(String text, float x, float y, Paint paint) {
        super.drawText(text, x, y, paint);
        timeUnitsElapsed += TIME_TO_DRAW_TEXT;
    }

    @Override
    public void drawText(char[] text, int index, int count, float x, float y, Paint paint) {
        super.drawText(text, index, count, x, y, paint);
        timeUnitsElapsed += TIME_TO_DRAW_TEXT;
    }

    @Override
    public void drawText(String text, int start, int end, float x, float y, Paint paint) {
        super.drawText(text, start, end, x, y, paint);
        timeUnitsElapsed += TIME_TO_DRAW_TEXT;
    }

    @Override
    public void drawText(CharSequence text, int start, int end, float x, float y, Paint paint) {
        super.drawText(text, start, end, x, y, paint);
        timeUnitsElapsed += TIME_TO_DRAW_TEXT;
    }
}
