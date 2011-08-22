package net.avh4.framework.uilayer.scene;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

class Image {

	private final int x;
	private final int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private final int width;
	private final int height;
	private final URL resource;

	/**
	 * @param imageName
	 *            The image specified must exist on the classpath in the default
	 *            package.
	 */
	public Image(final int x, final int y, final int width, final int height,
			final String imageName) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		resource = ClassLoader.getSystemResource(imageName);
		if (resource == null) {
			throw new IllegalArgumentException(String.format(
					"No such resource '%s'", imageName));
		}
	}

	public java.awt.Image getImage() {
		try {
			final BufferedImage image = ImageIO.read(resource);
			return image;
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
