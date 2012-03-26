package net.avh4.framework.data;

import org.picocontainer.DefaultPicoContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class DataStoreTest extends DataTestBase {

    private DefaultPicoContainer pico;
    private DataStore subject;

    protected void setUp() throws Exception {
        pico = new DefaultPicoContainer();
        super.setUp(pico);
        subject = pico.getComponent(DataStore.class);
    }

    public void testShouldSaveValue() throws Exception {
        subject.put("pageNumber", "2");

        assertThat(subject.get("pageNumber"), is("2"));
    }

    public void testShouldSaveValue2() throws Exception {
        subject.put("pageNumber", "14");

        assertThat(subject.get("pageNumber"), is("14"));
    }

    public void testShouldReturnNullForUnknownKey() throws Exception {
        subject.put("pageNumber", "14");

        assertThat(subject.get("lastDocument"), nullValue());
    }

    public void testShouldPersistData() throws Exception {
        subject.put("pageNumber", "7");

        setUp();

        assertThat(subject.get("pageNumber"), is("7"));
    }
}
