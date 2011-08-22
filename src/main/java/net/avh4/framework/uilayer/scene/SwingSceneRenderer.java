package net.avh4.framework.uilayer.scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Image;
import net.avh4.framework.uilayer.scene.Scene;

public class SwingSceneRenderer extends JComponent {

	private static final long serialVersionUID = 1L;
	private final SceneCreator creator;

	public SwingSceneRenderer(final SceneCreator creator) {
		this.creator = creator;
	}

	@Override
	public Dimension getPreferredSize() {
		return creator.getScene().getBounds().getSize();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Scene s = creator.getScene();
		final int height = s.getBounds().height;
		final int width = s.getBounds().width;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		for (final Image object : s) {
			g.drawImage(object.getImage(), object.getX(), object.getY(),
					object.getWidth(), object.getHeight(), null);
		}
	}
}
