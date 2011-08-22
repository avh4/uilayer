package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;

public class DemoGame implements SceneCreator {

	public static void main(final String[] args) {
		UILayer.main(new DemoGame());
	}

	private final Scene s;

	public DemoGame() {
		s = new Scene("UILayer Demo Game");
		s.addImage(0, 0, 800, 600, "background.jpg");
	}

	public Scene getScene() {
		return s;
	}
}
