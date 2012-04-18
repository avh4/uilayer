package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.Font;

public interface FontMetricsService {
    int getAscent(Font font);

    int stringWidth(Font font, String text);

    int getDescent(Font font);

    int getLineHeight(Font font);
}
