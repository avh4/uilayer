package net.avh4.framework.uilayer;

import javax.swing.JFrame;

import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SwingSceneRenderer;
import net.avh4.framework.uilayer.swing.input.SwingInputHandler;

public class UILayer {

	public static void main(final UI game) {
		UILayer.main(game, game, game);
	}

	public static void main(final SceneCreator game,
			final ClickReceiver receiver, final KeyReceiver keyReceiver) {
		final Scene scene = game.getScene();
		final String title = scene != null ? scene.getTitle() : "(No scene)";
		final JFrame window = new JFrame(title);
		final SwingSceneRenderer component = new SwingSceneRenderer(game);
		window.add(component);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final SwingInputHandler inputHandler = new SwingInputHandler(receiver,
				keyReceiver, component);
		component.addMouseListener(inputHandler);
		component.addKeyListener(inputHandler);

		component.requestFocusInWindow(); // Required to get keyboard focus for
											// the KeyListener to work

		window.setVisible(true);
	}

}
