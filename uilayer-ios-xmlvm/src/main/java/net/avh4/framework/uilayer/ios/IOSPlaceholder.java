package net.avh4.framework.uilayer.ios;

import net.avh4.framework.uilayer.scene.SceneObject;

import org.xmlvm.iphone.CGContext;
import org.xmlvm.iphone.CGRect;
import org.xmlvm.iphone.UIGraphics;

public class IOSPlaceholder extends SceneObject<CGRect> {

	private static final int MARGIN = 5;
	private final String name;
	private final float[] color = new float[] { .2f, .2f, .2f, 1 };
	private final float[] textColor = new float[] { 1, 1, 1, 1 };

	public IOSPlaceholder(final String name, final int x, final int y,
			final int width, final int height) {
		super(x, y, width, height);
		this.name = name;
	}

	@Override
	public void draw(final CGRect rect) {
		final CGContext g = UIGraphics.getCurrentContext();
		g.setFillColor(color);
		g.fillRect(new CGRect(x, y, width, height));
		g.setStrokeColor(new float[] { 0, 0, 0, 1 });
		g.strokeRect(new CGRect(x, y, width, height));

		g.selectFont("Helvetica", 10);
		g.setTextDrawingMode(CGContext.kCGTextFill);
		g.setFillColor(textColor);
		g.showTextAtPoint(x + MARGIN, y + height - MARGIN, name);
	}

}
