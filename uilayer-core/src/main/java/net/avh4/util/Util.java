package net.avh4.util;

import net.avh4.framework.uilayer.Color;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		return
                (0x000000ff & hash[0])
                        | (0x0000ff00 & (hash[1] << 8))
                        | (0x00ff0000 & (hash[2] << 16))
                        | (0xff000000);
	}

	/**
	 * Get a color that visually contrasts with the given color. This can be
	 * used for selecting a text color given an unknown background color.
	 */
	public static int getContrastingColor(final int color) {
		final int v = Color.getValue(color);
		if (v > 178) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}
}
