package net.avh4.framework.uilayer.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.android.TypefaceCache;

public class AndroidGraphicsOperations implements GraphicsOperations {
    private final Context context;
    private Canvas canvas;
    private static final TypefaceCache typefaceCache = new TypefaceCache();

    public AndroidGraphicsOperations(Context context) {
        this.context = context;
    }

    @Override
    public void drawRect(int leftX, int topY, int width, int height, int argbColor) {
        canvas.drawRect(leftX, topY, leftX + width, topY + height, loadColor(argbColor));
    }

    @Override
    public void drawText(String text, float leftX, float baselineY, Font font, int argbColor) {
        final Paint paint = loadColor(argbColor);
        loadFont(paint, font);
        canvas.drawText(text, leftX, baselineY, paint);
    }

    @Override
    public void translate(int deltaX, int deltaY) {
        canvas.translate(deltaX, deltaY);
    }

    @Override
    public void drawLine(int startX, int startY, int stopX, int stopY, int argbColor) {
        canvas.drawLine(startX, startY, stopX, stopY, loadColor(argbColor));
    }

    @Override
    public void drawOval(int leftX, int topY, int width, int height, int argbColor) {
        canvas.drawOval(new RectF(leftX, topY, leftX + width, topY + height), loadColor(argbColor));
    }

    @Override
    public void drawImage(String image, int destLeftX, int destTopY, int destRightX, int destBottomY,
                          int sourceLeftX, int sourceTopY, int sourceRightX, int sourceBottomY) {
    }

    private void loadFont(Paint paint, Font font) {
        loadFont(context, paint, font);
    }

    public static void loadFont(Context context, Paint paint, Font font) {
        paint.setTypeface(typefaceCache.get(context, font.getResourceName()));
        paint.setTextSize(font.getSize());
    }

    public static Paint loadColor(int color) {
        final Paint paint = new Paint();
        paint.setColor(color);
        return paint;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
