package net.avh4.framework.data;

import android.test.InstrumentationTestCase;
import net.avh4.framework.data.android.AndroidDataStore;
import org.picocontainer.MutablePicoContainer;

public class DataTestBase extends InstrumentationTestCase {

    protected void setUp(MutablePicoContainer pico) throws Exception {
        pico.addComponent(getInstrumentation().getTargetContext());
        pico.addComponent(AndroidDataStore.class);
    }
}
