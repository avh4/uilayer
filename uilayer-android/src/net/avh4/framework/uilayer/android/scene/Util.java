package net.avh4.framework.uilayer.android.scene;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.graphics.Color;

public class Util {

	private static MessageDigest digest;

	/**
	 * Generates a pseudo-random color given a string. This will always return
	 * the same color for the same string, even across multiple runs of the JVM.
	 */
	public static int getHashColor(final String string) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (final NoSuchAlgorithmException e) {
				throw new RuntimeException(
						"Could not instantiate a MessageDigest algorithm", e);
			}
		}
		digest.reset();
		final byte[] hash = digest.digest(string.getBytes());
		// The "& 0xff" is required to convert the byte to an unsigned int
		return Color.rgb(hash[2] & 0xff, hash[1] & 0xff, hash[0] & 0xff);
	}

	/**
	 * Get a color that visually contrasts with the given color. This can be
	 * used for selecting a text color given an unknown background color.
	 */
	public static int getContrastingColor(final int color) {
		final float hsv[] = new float[3];
		Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color),
				hsv);
		final float v = hsv[2];
		if (v > 0.7) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}
}
