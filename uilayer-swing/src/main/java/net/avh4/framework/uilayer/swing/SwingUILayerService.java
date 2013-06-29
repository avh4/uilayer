package net.avh4.framework.uilayer.swing;

import net.avh4.framework.async.Deferred;
import net.avh4.framework.async.Promise;
import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.File;
import net.avh4.framework.data.swing.SwingExternalStorage;
import net.avh4.framework.data.swing.SwingFile;
import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.TimerUpdate;
import net.avh4.framework.uilayer.UILayerService;
import net.avh4.framework.uilayer.input.ClickReceiver;
import net.avh4.framework.uilayer.input.KeyReceiver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class SwingUILayerService implements UILayerService {

    private static final HashMap<String, BufferedImage> imageCache = new HashMap<String, BufferedImage>();
    private static final HashMap<String, Font> fontCache = new HashMap<String, Font>();
    private final SwingExternalStorage swingExternalStorage = new SwingExternalStorage();
    private SwingSceneRenderer component;

    @Override
    public void run(final Element game, final ClickReceiver receiver,
                    final KeyReceiver keyReceiver, final TimerUpdate timerUpdate) {

//        final String title = scene != null ? scene.getTitle() : "(No scene)";
        final String title = game.toString();
        final JFrame window = new JFrame(title);
        final SwingGraphicsOperations graphicsOperations = new SwingGraphicsOperations();
        component = new SwingSceneRenderer(graphicsOperations, game);
        window.add(component);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final SwingInputHandler inputHandler = new SwingInputHandler(receiver,
                keyReceiver, component);
        component.addMouseListener(inputHandler);
        component.addKeyListener(inputHandler);
        component.addMouseMotionListener(inputHandler);

        component.requestFocusInWindow(); // Required to get keyboard focus for
        // the KeyListener to work

        window.setVisible(true);

        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimerUpdate.UpdateAction action = timerUpdate.time();
                switch (action) {
                    case NEEDS_REDRAW:
                        component.repaint();
                        break;
                }
            }
        });
        timer.start();
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

    @Override
    public int getPixel(String image, int x, int y) {
        return loadImage(image).getRGB(x, y);
    }

    @Override
    public int getFontHeight(net.avh4.framework.uilayer.Font font) {
        final Font awtFont = loadFont(font);

        final BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = (Graphics2D) image.getGraphics();
        return g2.getFontMetrics(awtFont).getHeight();
    }

    @Override
    public int measureText(net.avh4.framework.uilayer.Font font, String text) {
        final Font awtFont = loadFont(font);

        final BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = (Graphics2D) image.getGraphics();
        final FontMetrics metrics = g2.getFontMetrics(awtFont);
        final Rectangle2D bounds = metrics.getStringBounds(text, g2);
        return (int) bounds.getWidth();
    }

    @Override
    public <T> Promise<T> showChoices(final String title, final List<T> choices) {
        final Deferred<T> deferred = new Deferred<T>();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final Object response = JOptionPane.showInputDialog(null, null, title,
                        JOptionPane.QUESTION_MESSAGE, null, choices.toArray(), null);
                //noinspection unchecked
                deferred.resolve((T) response);
                component.repaint();
            }
        });
        return deferred;
    }

    @Override
    public Promise<File> showFileChooser(final String title) {
        final Deferred<File> deferred = new Deferred<File>();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle(title);
                chooser.showDialog(component, "Choose");
                java.io.File file = chooser.getSelectedFile();
                deferred.resolve(new SwingFile(null, file.getPath()));
                component.repaint();
            }
        });
        return deferred;
    }

    @Override
    public ExternalStorage getExternalStorage() {
        return swingExternalStorage;
    }

    public static Font loadFont(final net.avh4.framework.uilayer.Font font) {
        if (font == null) {
            throw new RuntimeException("No font specified.  Try Font.PFENNIG.");
        }

        final String customFontResource = font.getResourceName();
        return loadFont(customFontResource, font.getSize());
    }

    public static Font loadFont(final String customFontResource,
                                final int fontSize) {

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
            final Font awtFont = Font.createFont(Font.TRUETYPE_FONT, is)
                    .deriveFont(Font.PLAIN, fontSize);
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .registerFont(awtFont);
            fontCache.put(key, awtFont);
            return awtFont;
        } catch (final FontFormatException e) {
            throw new RuntimeException(String.format(
                    "Couldn't open custom font: %s", customFontResource), e);
        } catch (final IOException e) {
            throw new RuntimeException(String.format(
                    "Couldn't open custom font: %s", customFontResource), e);
        }
    }
}
