package net.avh4.framework.uilayer.swing;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import net.avh4.framework.uilayer.ClickReceiver;
import net.avh4.framework.uilayer.KeyReceiver;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SwingSceneRenderer;

public class SwingUILayerService implements UILayerService {

	private static final HashMap<String, BufferedImage> imageCache = new HashMap<String, BufferedImage>();
	private static final HashMap<String, Font> fontCache = new HashMap<String, Font>();

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

	public static BufferedImage loadImage(final String imageName) {
		if (imageCache.containsKey(imageName)) {
			return imageCache.get(imageName);
		}

		final URL resource = ClassLoader.getSystemResource(imageName);
		if (resource == null) {
			throw new IllegalArgumentException(String.format(
					"No such resource '%s'", imageName));
		}
		try {
			final BufferedImage image = ImageIO.read(resource);
			imageCache.put(imageName, image);
			return image;
		} catch (final IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(String.format(
					"Could not read image '%s'", imageName), e);
		}
	}

	@Override
	public int getImageWidth(final String image) {
		final BufferedImage i = loadImage(image);
		return i.getWidth();
	}

	@Override
	public int getImageHeight(final String image) {
		final BufferedImage i = loadImage(image);
		return i.getHeight();
	}

	public static Font loadFont(final String customFontResource,
			final int fontSize) {
		if (customFontResource == null) {
			throw new RuntimeException("No font specified.  Try Font.PFENNIG.");
		}

		final String key = customFontResource + ":" + fontSize;
		if (fontCache.containsKey(key)) {
			return fontCache.get(key);
		}

		final InputStream is = ClassLoader
				.getSystemResourceAsStream(customFontResource);
		if (is == null) {
			throw new RuntimeException(String.format(
					"Custom font resource not found: %s", customFontResource));
		}
		try {
			final Font font = Font.createFont(Font.TRUETYPE_FONT, is)
					.deriveFont(Font.PLAIN, fontSize);
			GraphicsEnvironment.getLocalGraphicsEnvironment()
					.registerFont(font);
			fontCache.put(key, font);
			return font;
		} catch (final FontFormatException e) {
			throw new RuntimeException(String.format(
					"Couldn't open custom font: %s", customFontResource), e);
		} catch (final IOException e) {
			throw new RuntimeException(String.format(
					"Couldn't open custom font: %s", customFontResource), e);
		}
	}

	public static void cacheImage(final String key, final BufferedImage image) {
		imageCache.put(key, image);
	}

}
