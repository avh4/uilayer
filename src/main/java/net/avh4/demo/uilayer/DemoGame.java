package net.avh4.demo.uilayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;

public class DemoGame implements SceneCreator, MouseListener {

	public static void main(final String[] args) {
		final DemoGame game = new DemoGame();
		UILayer.main(game, game);
	}

	private final Scene s;

	public DemoGame() {
		s = new Scene("UILayer Demo Game");
		s.addImage(0, 0, 800, 600, "background.jpg");
	}

	public Scene getScene() {
		return s;
	}

	public void mouseClicked(final MouseEvent e) {
		s.addImage(e.getX(), e.getY(), 10, 10, "background.jpg");
		e.getComponent().repaint();
	}

	public void mousePressed(final MouseEvent e) {
	}

	public void mouseReleased(final MouseEvent e) {
	}

	public void mouseEntered(final MouseEvent e) {
	}

	public void mouseExited(final MouseEvent e) {
	}
}
