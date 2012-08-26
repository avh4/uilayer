package net.avh4.framework.uilayer.swing;

import net.avh4.framework.uilayer.test.categories.FontRendering;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.ExcludeCategory(FontRendering.class)
@Suite.SuiteClasses({AllTests.class})
public class AllTestsExceptFontRendering {
}
