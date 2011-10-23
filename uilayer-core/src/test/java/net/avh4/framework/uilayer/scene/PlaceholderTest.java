package net.avh4.framework.uilayer.scene;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlaceholderTest {

	@Test
	public void testEquals() {
		final ScenePlaceholder a = new ScenePlaceholder("Item", 10, 20, 30, 40);
		final ScenePlaceholder a2 = new ScenePlaceholder("Item", 10, 20, 30, 40);
		final ScenePlaceholder b = new ScenePlaceholder("Item 2", 10, 20, 30,
				40);
		final ScenePlaceholder c = new ScenePlaceholder("Item", 11, 20, 30, 40);
		final ScenePlaceholder d = new ScenePlaceholder("Item", 10, 21, 30, 40);
		final ScenePlaceholder e = new ScenePlaceholder("Item", 10, 20, 31, 40);
		final ScenePlaceholder f = new ScenePlaceholder("Item", 10, 20, 30, 41);
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
