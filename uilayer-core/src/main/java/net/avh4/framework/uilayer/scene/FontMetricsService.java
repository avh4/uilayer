package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public interface FontMetricsService {
    float getAscent(Font font);

    float stringWidth(Font font, String text);

    float getDescent(Font font);

    float getLineHeight(Font font);

    float getAscender(Font font);
}
