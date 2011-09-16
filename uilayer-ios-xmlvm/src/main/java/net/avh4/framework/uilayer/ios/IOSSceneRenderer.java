package net.avh4.framework.uilayer.ios;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.scene.SceneObject;

import org.xmlvm.iphone.CGContext;
import org.xmlvm.iphone.CGRect;
import org.xmlvm.iphone.UIGraphics;
import org.xmlvm.iphone.UIView;

public class IOSSceneRenderer extends UIView {

	private final UI ui;

	public IOSSceneRenderer(final UI ui) {
		super(new CGRect(0, 0, 640, 960));
		this.ui = ui;
	}

	@Override
	public void drawRect(final CGRect rect) {
		final IOSScene s = (IOSScene) ui.getScene();

		final CGContext g = UIGraphics.getCurrentContext();
		final float[] black = new float[] { 0, 0, 0, 1 };
		g.setFillColor(black);
		g.fillRect(new CGRect(0, 0, 640, 960));

		for (final SceneObject<CGRect> object : s) {
			object.draw(rect);
		}
	}

}
