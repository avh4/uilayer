package net.avh4.framework.uilayer.scene;

import android.content.Context;
import android.graphics.Paint;
import net.avh4.framework.uilayer.Font;

import static android.graphics.Paint.FontMetrics;

public class AndroidFontMetricsService implements FontMetricsService {
    private final Context context;

    public AndroidFontMetricsService(Context context) {
        this.context = context;
    }

    @Override
    public float getAscent(Font font) {
        return -getFontMetrics(font).ascent;
    }

    @Override
    public float stringWidth(Font font, String text) {
        return getPaint(font).measureText(text);
    }

    @Override
    public float getDescent(Font font) {
        return getFontMetrics(font).descent;
    }

    @Override
    public float getLineHeight(Font font) {
        final FontMetrics fm = getFontMetrics(font);
        return -fm.ascent + fm.descent + fm.leading;
    }

    private FontMetrics getFontMetrics(Font font) {
        final Paint paint = getPaint(font);
        return paint.getFontMetrics();
    }

    private Paint getPaint(Font font) {
        final Paint paint = new Paint();
        AndroidGraphicsOperations.loadFont(context, paint, font);
        return paint;
    }
}
