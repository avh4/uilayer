package net.avh4.framework.data.test;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ClasspathResourcesExternalStorageTest {

    private ClasspathResourcesExternalStorage subject;

    @Before
    public void setUp() throws Exception {
        subject = new ClasspathResourcesExternalStorage("ClasspathResourcesExternalStorageTest");
    }

    @Test
    public void getFiles_withNoAddedFiles_shouldBeEmpty() throws Exception {
        assertThat(subject.getFiles(), isEmpty());
    }

    @Test
    public void getFiles_withManyAddedFiles_shouldReturnTheFile() throws Exception {
        subject.allowFile("Treasure Island.txt");
        subject.allowFile("The Demo Book.txt");

        assertThat(subject.getFiles(), is(Arrays.asList(
                "Treasure Island.txt",
                "The Demo Book.txt"
        )));
    }

    @Test
    public void getString_forFileThatIsNotAllowed_shouldReturnNull() throws Exception {
        assertThat(subject.getString("Romeo and Juliet.txt"), nullValue());
    }

    @Test
    public void getString_forAllowedFile_shouldFilesContents() throws Exception {
        subject.allowFile("Romeo and Juliet.txt");

        assertThat(subject.getString("Romeo and Juliet.txt"), startsWith("Romeo and Juliet\n\nACT I\n"));
    }

    private Matcher<Iterable<?>> isEmpty() {
        return new TypeSafeMatcher<Iterable<?>>() {
            @Override
            protected boolean matchesSafely(Iterable<?> item) {
                return !item.iterator().hasNext();
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
