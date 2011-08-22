package net.avh4.framework.uilayer;

import java.awt.event.MouseListener;

import javax.swing.JFrame;

import net.avh4.framework.uilayer.scene.SwingSceneRenderer;

public class UILayer {

	public static void main(final SceneCreator game) {
		UILayer.main(game, null);
	}

	public static void main(final SceneCreator game,
			final MouseListener mouseListener) {
		final JFrame window = new JFrame(game.getScene().getTitle());
		final SwingSceneRenderer component = new SwingSceneRenderer(game);
		window.add(component);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (mouseListener != null) {
			component.addMouseListener(mouseListener);
		}

		window.setVisible(true);
	}

}
