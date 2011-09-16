package net.avh4.framework.uilayer.swing;

import javax.swing.JFrame;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.KeyReceiver;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.swing.input.SwingInputHandler;
import net.avh4.framework.uilayer.swing.scene.SwingScene;
import net.avh4.framework.uilayer.swing.scene.SwingSceneRenderer;

public class SwingUILayerService implements UILayerService {

	@Override
	public void run(final SceneCreator game, final ClickReceiver receiver,
			final KeyReceiver keyReceiver) {

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

	@Override
	public Scene newScene(final String title) {
		return new SwingScene(title);
	}

}
