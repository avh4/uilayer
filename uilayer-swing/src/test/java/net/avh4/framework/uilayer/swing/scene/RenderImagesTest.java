package net.avh4.framework.uilayer.swing.scene;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.junit.Assert.assertThat;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import net.avh4.framework.uilayer.scene.SceneImage;

import org.junit.Test;

public class RenderImagesTest extends RenderTestBase {

	@Test
	public void testRenderResourceImage() throws Exception {
		scene.addImage(100, 100, 50, 50, "tile1.png");
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderProvidedImage() throws Exception {
		final Image image = new BufferedImage(50, 50,
				BufferedImage.TYPE_INT_ARGB);
		final Graphics g = image.getGraphics();
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 50, 50);
		g.setColor(Color.PINK);
		g.fillRect(10, 10, 50, 50);
		g.dispose();
	
		scene.addImage(100, 100, 50, 50, image);
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderMultipleImages() throws Exception {
		scene.addImage(100, 100, 150, 150, "tile1.png");
		scene.addImage(150, 150, 75, 75, "tile2.png");
		scene.addImage(100, 300, 16, 16, "tile1.png");
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderRepositionsedImage() throws Exception {
		final SceneImage image = scene.addImage(100, 100, 50, 50, "tile1.png");
		image.setPosition(0, 0);
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderClippedImage() throws Exception {
		scene.addImage(100, 100, 50, 50, "tile1.png", 0, 0, 25, 25);
		assertThat(subject, isApproved());
	}

	@Test
	public void testRenderReclippedClippedImage() throws Exception {
		final SceneImage image = scene.addImage(100, 100, 50, 50, "tile1.png",
				0, 0, 25, 25);
		image.setClipPosition(25, 25);
		assertThat(subject, isApproved());
	}

}
