package net.avh4.framework.data;

import org.picocontainer.DefaultPicoContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ExternalStorageTest extends DataTestBase {

    private ExternalStorage subject;

    protected void setUp() throws Exception {
        DefaultPicoContainer pico = new DefaultPicoContainer();
        super.setUp(pico);
        subject = pico.getComponent(ExternalStorage.class);
    }

    public void testShouldReadStringFromFile() {
        super.createTestFile("hello-world.txt", "Hello, World!\n");

        final String string = subject.getFile("hello-world.txt").getContents();

        assertThat(string, is("Hello, World!\n"));
    }
}
