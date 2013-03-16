package net.avh4.framework.uilayer.android;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import net.avh4.framework.async.Promise;
import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.File;
import net.avh4.framework.data.android.AndroidExternalStorage;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.input.ClickReceiver;
import net.avh4.framework.uilayer.input.KeyReceiver;
import org.apache.commons.lang.NotImplementedException;

import java.util.List;

public class AndroidUILayerService implements UILayerService {

    Context context;
    private final ExternalStorage externalStorage = new AndroidExternalStorage();

    public void run(SceneCreator game, ClickReceiver receiver, KeyReceiver keyReceiver) {
        throw new RuntimeException(
                "Android applications do not implement a main entry point.  " +
                        "Subclass AndroidSceneRendererActivity instead.");
    }

    public void init(Context context) {
        this.context = context;
    }

    public int getImageWidth(String image) {
        return 0;
    }

    public int getImageHeight(String image) {
        return 0;
    }

    @Override
    public int getPixel(String image, int x, int y) {
        throw new NotImplementedException(); // TODO
    }

    public int getFontHeight(Font font) {
        final Paint paint = new Paint();
        paint.setTypeface(Typeface.createFromAsset(context.getAssets(), font.getResourceName()));
        paint.setTextSize(font.getSize());
        final Paint.FontMetrics metrics = paint.getFontMetrics();
        return (int) Math.ceil(-metrics.ascent + metrics.descent + metrics.leading);
    }

    public int measureText(Font font, String text) {
        final Paint paint = new Paint();
        paint.setTypeface(Typeface.createFromAsset(context.getAssets(), font.getResourceName()));
        paint.setTextSize(font.getSize());
        return (int) Math.ceil(paint.measureText(text));
    }

    @Override
    public <T> Promise<T> showChoices(String title, List<T> choices) {
        throw new NotImplementedException(); // TODO
    }

    @Override
    public Promise<File> showFileChooser(String title) {
        throw new NotImplementedException(); // TODO
    }

    @Override
    public ExternalStorage getExternalStorage() {
        return externalStorage;
    }
}
