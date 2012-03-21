package net.avh4.util.imagecomparison.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ImageComparison {

    private static final int INTS_PER_PIXEL = 1;

    public static boolean matchesImage(final Bitmap itemImage,
                                       final Bitmap referenceImage, final String filename) {
        // Compare the image sizes
        if (itemImage.getWidth() != referenceImage.getWidth()
                || itemImage.getHeight() != referenceImage.getHeight()) {
            ImageComparison.write(itemImage, filename);
            return false;
        }

        // Compare the image data
        final int width = itemImage.getWidth();
        final int height = itemImage.getHeight();
        for (int y = 0; y < height; y++) {
            final int itemPixels[] = new int[INTS_PER_PIXEL * width];
            final int referencePixels[] = new int[INTS_PER_PIXEL * width];
            itemImage.getPixels(itemPixels, 0, width, 0, y, width, 1);
            referenceImage.getPixels(referencePixels, 0, width, 0, y, width, 1);
            for (int i = 0; i < INTS_PER_PIXEL * width; i++) {
                if (itemPixels[i] != referencePixels[i]) {
                    ImageComparison.write(itemImage, filename);
                    return false;
                }
            }
        }

        return true;
    }

    static Bitmap read(final Context context, final String imageName) {
        final int drawableId = context.getResources().getIdentifier(imageName.replaceFirst(".png$", ""), "drawable", "net.avh4.framework.uilayer.test");
        if (drawableId == 0) {
            return null;
        }
        final Drawable drawable = context.getResources().getDrawable(drawableId);
        final Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        return bitmap;
    }

    private static void write(final Bitmap image, final String filename) {
        try {
            final File file = new File(
                    Environment.getExternalStorageDirectory(), filename);
            final FileOutputStream out = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, out);
        } catch (final FileNotFoundException e) {
            throw new RuntimeException("Couldn't write " + filename, e);
        }
    }

    public static boolean matches(final Context context, final Object item,
                                  final String referenceFilename, final String outputFilename) {
        final Bitmap expectedImage = read(context, referenceFilename);
        return matches(item, expectedImage, outputFilename);
    }

    public static boolean matches(final Object actual,
                                  final Bitmap expectedImage, final String outputFilename) {
        final Bitmap actualImage = ImageComparison.getImage(actual);
        if (actualImage == null) {
            return false;
        } else if (expectedImage == null) {
            write(actualImage, outputFilename);
            return false;
        } else {
            return matchesImage(actualImage, expectedImage, outputFilename);
        }
    }

    private static Bitmap drawView(final View view) {
        final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private static Bitmap getImage(final Object item) {
        if (item instanceof Activity) {
            return drawView(((Activity) item).getWindow().getDecorView());
        } else if (item instanceof View) {
            return drawView((View) item);
        } else if (item instanceof BitmapDrawable) {
            return ((BitmapDrawable) item).getBitmap();
        } else if (item instanceof Drawable) {
            throw new RuntimeException(
                    "comparison of non-bitmap Drawables is not yet implemented");
        } else if (item instanceof Bitmap) {
            return (Bitmap) item;
        } else {
            throw new RuntimeException("Don't know how to make an image of "
                    + item);
        }
    }

}
