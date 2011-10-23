package net.avh4.framework.uilayer;

public class Color {

	public static final int WHITE = 0xffffff;
	public static final int BLUE = 0xff0000ff;
	public static final int BLACK = 0xff000000;
	public static final int RED = 0xffff0000;
	public static final int YELLOW = 0xffffff00;

	public static int getRed(final int color) {
		return 0xff & (color >> 16);
	}

	public static int getGreen(final int color) {
		return 0xff & (color >> 8);
	}

	public static int getBlue(final int color) {
		return 0xff & (color);
	}

	public static int getValue(final int color) {
		// http://www.cs.rit.edu/~ncs/color/t_convert.html
		return Math.max(getRed(color),
				Math.max(getGreen(color), getBlue(color)));
	}

}
