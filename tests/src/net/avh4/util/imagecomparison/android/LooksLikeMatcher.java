package net.avh4.util.imagecomparison.android;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class LooksLikeMatcher extends BaseMatcher<Object> {

	private final String filename;
	private final Bitmap referenceImage;
	private final Rect referenceImageSize;

	public LooksLikeMatcher(final Context context, final String filename) {
		this.filename = filename;
		referenceImage = ImageComparison.read(context, filename);
		if (referenceImage == null) {
			referenceImageSize = null;
		} else {
			referenceImageSize = new Rect(0, 0, referenceImage.getWidth(),
					referenceImage.getHeight());
		}
	}

	public boolean matches(final Object item) {
		return ImageComparison.matches(item, referenceImage, filename);
	}

	public void describeTo(final Description description) {
		if (referenceImage == null) {
			description.appendText(String.format(
					"reference image \"%s\" to exist", filename));
		} else {
			description.appendText(String.format(
					"something that looks like %s (%dx%d)", filename,
					referenceImageSize.width(), referenceImageSize.height()));
		}
	}

}