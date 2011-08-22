package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.ScenegraphCreator;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.model.Scenegraph;

public class DemoGame implements ScenegraphCreator {

	public static void main(final String[] args) {
		UILayer.main(new DemoGame());
	}

	private final Scenegraph s;

	public DemoGame() {
		s = new Scenegraph("UILayer Demo Game");
		s.addImage(0, 0, 800, 600, "background.jpg");
	}

	public Scenegraph getScenegraph() {
		return s;
	}
}
