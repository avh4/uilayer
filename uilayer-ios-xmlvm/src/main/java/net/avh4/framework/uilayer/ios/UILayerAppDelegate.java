package net.avh4.framework.uilayer.ios;

import net.avh4.framework.uilayer.UI;

import org.xmlvm.iphone.UIApplication;
import org.xmlvm.iphone.UIApplicationDelegate;
import org.xmlvm.iphone.UIScreen;
import org.xmlvm.iphone.UIWindow;

public class UILayerAppDelegate extends UIApplicationDelegate {

	private static UI nextUi;
	private final UI ui;

	public static void setNextUi(final UI ui) {
		nextUi = ui;
	}

	public UILayerAppDelegate() {
		ui = nextUi;
	}

	@Override
	public void applicationDidFinishLaunching(final UIApplication app) {
		final UIWindow window = new UIWindow(UIScreen.mainScreen().getBounds());

		// final UIView mainView = new UIView(window.getFrame());

		final IOSSceneRenderer view = new IOSSceneRenderer(ui);

		window.addSubview(view);

		window.makeKeyAndVisible();
	}
}
