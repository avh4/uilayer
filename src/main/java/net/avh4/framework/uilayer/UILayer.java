package net.avh4.framework.uilayer;

import javax.swing.JFrame;

public class UILayer {

	public static void main(final SceneCreator game) {
		final JFrame window = new JFrame(game.getScene().getTitle());
		window.add(new SwingSceneRenderer(game));
		window.pack();
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
