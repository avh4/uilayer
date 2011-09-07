package net.avh4.framework.uilayer.swing.scene;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import net.avh4.framework.uilayer.swing.scene.SwingPlaceholder;

import org.junit.Test;

public class PlaceholderTest {

	@Test
	public void testEquals() {
		final SwingPlaceholder a = new SwingPlaceholder("Item", 10, 20, 30, 40);
		final SwingPlaceholder a2 = new SwingPlaceholder("Item", 10, 20, 30, 40);
		final SwingPlaceholder b = new SwingPlaceholder("Item 2", 10, 20, 30, 40);
		final SwingPlaceholder c = new SwingPlaceholder("Item", 11, 20, 30, 40);
		final SwingPlaceholder d = new SwingPlaceholder("Item", 10, 21, 30, 40);
		final SwingPlaceholder e = new SwingPlaceholder("Item", 10, 20, 31, 40);
		final SwingPlaceholder f = new SwingPlaceholder("Item", 10, 20, 30, 41);
		assertTrue(a.equals(a2));
		assertTrue(a2.equals(a2));
		assertFalse(a.equals(b));
		assertFalse(b.equals(a));
		assertFalse(a.equals(c));
		assertFalse(c.equals(a));
		assertFalse(a.equals(d));
		assertFalse(d.equals(a));
		assertFalse(a.equals(e));
		assertFalse(e.equals(a));
		assertFalse(a.equals(f));
		assertFalse(f.equals(a));
	}

}
