package net.avh4.framework.uilayer.android.scene;

import android.graphics.Canvas;
import android.graphics.Paint;

public class AndroidPlaceholder extends AndroidSceneObject {

	private static final int MARGIN = 5;
	private final String name;
	private final Paint color;
	private final Paint textColor;

	public AndroidPlaceholder(final String name, final int x, final int y,
			final int width, final int height) {
		super(x, y, width, height);
		this.name = name;
		color = new Paint();
		color.setColor(Util.getHashColor(name));
		textColor = new Paint();
		textColor.setColor(Util.getContrastingColor(color.getColor()));
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		final AndroidPlaceholder b = (AndroidPlaceholder) obj;
		if (b == null) {
			return false;
		}
		return name.equals(b.name) && x == b.x && y == b.y && width == b.width
				&& height == b.height;
	}

	@Override
	public void draw(final Canvas g) {
		g.drawRect(x, y, x + width, y + height, color);
		g.drawText(name, x + MARGIN, y + height - MARGIN, textColor);
	}
}
