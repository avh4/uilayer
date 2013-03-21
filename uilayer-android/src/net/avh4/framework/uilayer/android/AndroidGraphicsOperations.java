package net.avh4.framework.uilayer.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.GraphicsOperations;

public class AndroidGraphicsOperations implements GraphicsOperations {
    private final Context context;
    private Canvas canvas;
    private static final TypefaceCache typefaceCache = new TypefaceCache();

    public AndroidGraphicsOperations(Context context) {
        this.context = context;
    }

    @Override
    public void drawRect(double leftX, double topY, double width, double height, int argbColor) {
        canvas.drawRect((float) leftX, (float) topY, (float) (leftX + width), (float) (topY + height),
                loadColor(argbColor));
    }

    @Override
    public void drawText(String text, double leftX, double baselineY, Font font, int argbColor) {
        final Paint paint = loadColor(argbColor);
        loadFont(paint, font);
        canvas.drawText(text, (float) leftX, (float) baselineY, paint);
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        canvas.translate((float) deltaX, (float) deltaY);
    }

    @Override
    public void drawLine(int startX, int startY, int stopX, int stopY, int argbColor) {
        canvas.drawLine(startX, startY, stopX, stopY, loadColor(argbColor));
    }

    @Override
    public void drawOval(double leftX, double topY, double width, double height, int argbColor) {
        RectF frame = new RectF((float) leftX, (float) topY, (float) (leftX + width), (float) (topY + height));
        canvas.drawOval(frame, loadColor(argbColor));
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
