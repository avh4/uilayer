package net.avh4.framework.uilayer;

public class Color {

    public static final int BLACK = 0xff000000;
    public static final int BLUE = 0xff0000ff;
    public static final int GREEN = 0xff00ff00;
    public static final int GRAY = 0xff7f7f7f;
    public static final int GREY = GRAY;
    public static final int RED = 0xffff0000;
    public static final int WHITE = 0xffffffff;
    public static final int YELLOW = 0xffffff00;
    public static final int DARK_GRAY = 0xff404040;

    public static int getAlpha(final int color) {
        return 0xff & (color >> 24);
    }

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
        return Math.max(getRed(color), Math.max(getGreen(color), getBlue(color)));
    }

    public static int darken(final double factor, final int color) {
        return (0xff000000 & color)
                | ((int) ((0x00ff0000 & color) * (1 - factor)) & 0x00ff0000)
                | ((int) ((0x0000ff00 & color) * (1 - factor)) & 0x0000ff00)
                | ((int) ((0x000000ff & color) * (1 - factor)) & 0x000000ff);
    }

    public static int fromRGB(int r, int g, int b) {
        return 0xff000000
                | (r & 0xff) << 16
                | (g & 0xff) << 8
                | (b & 0xff);
    }

    public static int alpha(int color, int a) {
        return 0x00ffffff & color
                | (a & 0xff) << 24;
    }

    private static double hue2rgb(double p, double q, double t) {
        if (t < 0) t += 1;
        if (t > 1) t -= 1;
        if (t < 1 / 6f) return p + (q - p) * 6 * t;
        if (t < 1 / 2f) return q;
        if (t < 2 / 3f) return p + (q - p) * (2 / 3f - t) * 6;
        return p;
    }


    public static int fromHSL(double hueDegrees, double s, double l) {
        final double h = hueDegrees / 360f;
        final double r;
        final double g;
        final double b;

        if (s == 0) {
            r = g = b = l;
        } else {
            final double q = l < 0.5 ? l * (1 + s) : l + s - l * s;
            final double p = 2 * l - q;
            r = hue2rgb(p, q, h + 1 / 3f);
            g = hue2rgb(p, q, h);
            b = hue2rgb(p, q, h - 1 / 3f);
        }

        return fromRGB((int) Math.round(r * 255), (int) Math.round(g * 255), (int) Math.round(b * 255));
    }
}
