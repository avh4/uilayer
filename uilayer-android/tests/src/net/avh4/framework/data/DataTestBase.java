package net.avh4.framework.data;

import android.os.Environment;
import android.test.InstrumentationTestCase;
import net.avh4.framework.data.android.AndroidDataStore;
import net.avh4.framework.data.android.AndroidExternalStorage;
import org.apache.commons.io.IOUtils;
import org.picocontainer.MutablePicoContainer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataTestBase extends InstrumentationTestCase {

    protected void setUp(MutablePicoContainer pico) throws Exception {
        pico.addComponent(getInstrumentation().getTargetContext());
        pico.addComponent(AndroidDataStore.class);
        pico.addComponent(AndroidExternalStorage.class);
    }

    public void createTestFile(String filename, String content) {
        final File dir = Environment.getExternalStorageDirectory();
        final File file = new File(dir, filename);
        try {
            final FileOutputStream fos = new FileOutputStream(file);
            IOUtils.write(content, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("If running in an emulator, make sure the AVD has an SD card");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
