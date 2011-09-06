package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;

public class DemoGame implements SceneCreator, ClickReceiver {

	public static void main(final String[] args) {
		final DemoGame game = new DemoGame();
		UILayer.main(game, game);
	}

	private final Scene s;

	public DemoGame() {
		s = new Scene("UILayer Demo Game");
		s.addImage(0, 0, 800, 600, "background.jpg");
	}

	@Override
	public Scene getScene() {
		return s;
	}

	@Override
	public void click(final int x, final int y) {
		s.addImage(x, y, 10, 10, "background.jpg");
	}
}
