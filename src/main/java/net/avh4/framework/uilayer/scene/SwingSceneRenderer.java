package net.avh4.framework.uilayer.scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import net.avh4.framework.uilayer.SceneCreator;

public class SwingSceneRenderer extends JComponent {

	private static final long serialVersionUID = 1L;
	private final SceneCreator creator;

	public SwingSceneRenderer(final SceneCreator creator) {
		this.creator = creator;
	}

	@Override
	public Dimension getPreferredSize() {
		final Scene scene = creator.getScene();
		if (scene == null) {
			return new Dimension(100, 100);
		} else {
			return scene.getBounds().getSize();
		}
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Scene s = creator.getScene();
		if (s == null) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Color.BLACK);
			g.drawString("(No scene)", 0, 20);
			return;
		}
		final int height = s.getBounds().height;
		final int width = s.getBounds().width;

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		for (final SwingSceneObject object : s) {
			object.draw(g);
		}
	}
}
