package net.avh4.framework.uilayer.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.UI;

import static android.graphics.Paint.FontMetrics;

public class AndroidSceneRenderer extends View {

    private final UI mUi;

    public AndroidSceneRenderer(final Context context, final UI ui) {
        super(context);
        if (ui == null) {
            throw new RuntimeException("UI must not be null");
        }
        mUi = ui;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        final Scene s = mUi.getScene();
        final Paint paint = new Paint();

        if (s == null) {
            paint.setColor(Color.GRAY);
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
            paint.setColor(Color.BLACK);
            canvas.drawText("(No scene)", 0, 20, paint);
            return;
        }
        final int height = s.getHeight();
        final int width = s.getWidth();

        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, width, height, paint);

        for (final SceneElement object : s) {
            draw(canvas, object);
        }
    }

    private void draw(Canvas canvas, SceneElement e) {
        if (e instanceof CompositeSceneElement) {
            drawComposite(canvas, (CompositeSceneElement) e);
        } else if (e instanceof ScenePlaceholder) {
            drawPlaceholder(canvas, (ScenePlaceholder) e);
        } else if (e instanceof SceneLabel) {
            drawLabel(canvas, (SceneLabel) e);
        } else if (e instanceof SceneLine) {
            drawLine(canvas, (SceneLine) e);
        } else if (e instanceof SceneOval) {
            drawOval(canvas, (SceneOval) e);
        } else if (e instanceof SceneRect) {
            drawRect(canvas, (SceneRect) e);
        } else if (e instanceof SceneText) {
            drawText(canvas, (SceneText) e);
        } else {
            final Paint paint = loadColor(0xffffffff);
            canvas.drawRect(e.x, e.y, 10, 10, paint);
        }
    }

    private void drawComposite(Canvas canvas, CompositeSceneElement e) {
        canvas.translate(e.x, e.y);
        for (final SceneElement object : e.getSceneElements()) {
            draw(canvas, object);
        }
        canvas.translate(-e.x, -e.y);
    }

    private void drawLabel(final Canvas canvas, final SceneLabel e) {
        final Paint paint = loadColor(e.color);
        loadFont(paint, e.font);

        final FontMetrics fontMetrics = paint.getFontMetrics();
        final float labelWidth = paint.measureText(e.text);

        final float x = e.x - labelWidth / 2;
        final float y = e.y + Math.abs(fontMetrics.top);

        canvas.drawText(e.text, x, y, paint);
    }

    private void loadFont(Paint paint, Font font) {
        paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), font.getResourceName()));
        paint.setTextSize(font.getSize());
    }

    private void drawLine(Canvas canvas, SceneLine e) {
        final Paint color = loadColor(e.color);
        canvas.drawLine(e.x1, e.y1, e.x2, e.y2, color);
    }

    private void drawRect(Canvas canvas, SceneRect e) {
        final Paint color = loadColor(e.color);
        canvas.drawRect(e.x, e.y, e.x + e.width, e.y + e.height, color);
    }

    private void drawOval(Canvas canvas, SceneOval e) {
        final Paint color = loadColor(e.color);
        canvas.drawOval(new RectF(e.x, e.y, e.x + e.width, e.y + e.height), color);
    }

    private void drawText(Canvas canvas, SceneText e) {
        final Paint paint = loadColor(e.color);
        loadFont(paint, e.font);

        final FontMetrics fontMetrics = paint.getFontMetrics();
        final float lineHeight = -fontMetrics.ascent + fontMetrics.descent + fontMetrics.leading;

        float curX = e.x;
        float curY = e.y + (-fontMetrics.ascent);

        final String[] words = e.text.split(" ");

        for (final String word : words) {
            // Find out the width of the word.
            final float wordWidth = paint.measureText(word);

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth >= e.x + e.width) {
                curY += lineHeight;
                curX = e.x;
            }

            canvas.drawText(word, curX, curY, paint);

            // Move over to the right for next word.
            curX += paint.measureText(word + " ");
        }
    }

    public void drawPlaceholder(final Canvas canvas, final ScenePlaceholder e) {
        final int MARGIN = 5;
        final Paint color = loadColor(e.color);
        final Paint textColor = loadColor(e.textColor);
        canvas.drawRect(e.x, e.y, e.x + e.width, e.y + e.height, color);
        canvas.drawText(e.name, e.x + MARGIN, e.y + e.height - MARGIN, textColor);
    }

    private Paint loadColor(int color) {
        final Paint paint = new Paint();
        paint.setColor(color);
        return paint;
    }
}
