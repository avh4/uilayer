package net.avh4.demo.uilayer;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;

public class DemoGame implements UI {

	public static void main(final String[] args) {
		final DemoGame game = new DemoGame();
		UILayer.run(game);
	}

	private Scene s;

	public DemoGame() {
		s = UILayer.newScene("UILayer Demo Game");
		s.addImage(0, 0, 640, 960, "background.jpg");
		click(100, 100);
	}

	@Override
	public Scene getScene() {
		return s;
	}

	@Override
	public void click(final int x, final int y) {
		s.addPlaceholder("Box", x, y, 50, 50);
	}

	@Override
	public void key(final int keyCode) {
		// if (keyCode == KeyEvent.VK_SPACE) {
		s = UILayer.newScene("UILayer Demo Game");
		s.addImage(0, 0, 640, 960, "background.jpg");
		// }
	}

}
