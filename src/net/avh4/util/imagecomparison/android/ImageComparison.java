package net.avh4.util.imagecomparison.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.view.View;

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
		try {
			InputStream is;
			is = context.getAssets().open(imageName);
			return BitmapFactory.decodeStream(is);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Couldn't open image: " + imageName, e);
		}
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

	private static Bitmap drawComponent(final View c) {
		final boolean wasDrawingCacheEnabled = c.isDrawingCacheEnabled();
		c.setDrawingCacheEnabled(true);
		final Bitmap b = Bitmap.createBitmap(c.getDrawingCache());
		c.isDrawingCacheEnabled();
		c.setDrawingCacheEnabled(wasDrawingCacheEnabled);
		return b;
	}

	private static Bitmap getImage(final Object item) {
		if (item instanceof Activity) {
			return drawComponent(((Activity) item).getWindow().getDecorView());
		} else if (item instanceof View) {
			return drawComponent((View) item);
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
