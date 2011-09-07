package net.avh4.framework.uilayer.swing.scene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.avh4.framework.uilayer.scene.SceneImage;

class SwingImage extends SwingSceneObject implements SceneImage {

	private final Image image;
	private static final HashMap<String, BufferedImage> cache = new HashMap<String, BufferedImage>();
	private int clipX;
	private int clipY;
	private final int clipWidth;
	private final int clipHeight;

	/**
	 * @param imageName
	 *            The image specified must exist on the classpath in the default
	 *            package.
	 */
	public SwingImage(final int x, final int y, final int width,
			final int height, final String imageName) {
		super(x, y, width, height);
		image = loadImage(imageName);
		clipX = 0;
		clipY = 0;
		clipWidth = image.getWidth(null);
		clipHeight = image.getHeight(null);
	}

	/**
	 * @param imageName
	 *            The image specified must exist on the classpath in the default
	 *            package.
	 */
	public SwingImage(final int x, final int y, final int width,
			final int height, final String imageName, final int clipX,
			final int clipY, final int clipWidth, final int clipHeight) {
		super(x, y, width, height);
		image = loadImage(imageName);
		this.clipX = clipX;
		this.clipY = clipY;
		this.clipWidth = clipWidth;
		this.clipHeight = clipHeight;
	}

	public SwingImage(final int x, final int y, final int width,
			final int height, final Image image) {
		super(x, y, width, height);
		this.image = image;
		clipX = 0;
		clipY = 0;
		clipWidth = image.getWidth(null);
		clipHeight = image.getHeight(null);
	}

	public SwingImage(final int x, final int y, final int width,
			final int height, final Image image, final int clipX,
			final int clipY, final int clipWidth, final int clipHeight) {
		super(x, y, width, height);
		this.image = image;
		this.clipX = clipX;
		this.clipY = clipY;
		this.clipWidth = clipWidth;
		this.clipHeight = clipHeight;
	}

	private static BufferedImage loadImage(final String imageName) {
		if (cache.containsKey(imageName)) {
			return cache.get(imageName);
		}

		final URL resource = ClassLoader.getSystemResource(imageName);
		if (resource == null) {
			throw new IllegalArgumentException(String.format(
					"No such resource '%s'", imageName));
		}
		try {
			final BufferedImage image = ImageIO.read(resource);
			cache.put(imageName, image);
			return image;
		} catch (final IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(String.format(
					"Could not read image '%s'", imageName), e);
		}
	}

	public java.awt.Image getImage() {
		return image;
	}

	@Override
	public void draw(final Graphics g) {
		g.drawImage(getImage(), x, y, x + width, y + height, clipX, clipY,
				clipX + clipWidth, clipY + clipHeight, null);
	}

	@Override
	public void setPosition(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setClipPosition(final int clipX, final int clipY) {
		this.clipX = clipX;
		this.clipY = clipY;
	}

}
