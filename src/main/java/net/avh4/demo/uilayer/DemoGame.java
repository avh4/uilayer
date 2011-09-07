package net.avh4.demo.uilayer;

import java.awt.event.KeyEvent;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.swing.scene.SwingScene;

public class DemoGame implements UI {

	public static void main(final String[] args) {
		final DemoGame game = new DemoGame();
		UILayer.main(game);
	}

	private SwingScene s;

	public DemoGame() {
		s = new SwingScene("UILayer Demo Game");
		s.addImage(0, 0, 800, 600, "background.jpg");
	}

	@Override
	public SwingScene getScene() {
		return s;
	}

	@Override
	public void click(final int x, final int y) {
		s.addPlaceholder("Box", x, y, 50, 50);
	}

	@Override
	public void key(final int keyCode) {
		// throw new RuntimeException("Code: " + keyCode);
		if (keyCode == KeyEvent.VK_SPACE) {
			s = new SwingScene("UILayer Demo Game");
			s.addImage(0, 0, 800, 600, "background.jpg");
		}
	}
}
