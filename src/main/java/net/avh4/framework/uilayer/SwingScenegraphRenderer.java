package net.avh4.framework.uilayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import net.avh4.framework.uilayer.model.Image;
import net.avh4.framework.uilayer.model.Scenegraph;

public class SwingScenegraphRenderer extends JComponent {

	private static final long serialVersionUID = 1L;
	private final ScenegraphCreator creator;

	public SwingScenegraphRenderer(final ScenegraphCreator creator) {
		this.creator = creator;
	}

	@Override
	public Dimension getPreferredSize() {
		return creator.getScenegraph().getBounds().getSize();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		final Scenegraph s = creator.getScenegraph();
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
