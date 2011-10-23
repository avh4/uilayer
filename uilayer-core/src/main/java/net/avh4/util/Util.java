package net.avh4.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.avh4.framework.uilayer.Color;

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
		return hash[0] + (hash[1] << 8) + (hash[2] << 16);
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
