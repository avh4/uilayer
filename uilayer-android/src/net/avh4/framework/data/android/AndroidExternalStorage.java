package net.avh4.framework.data.android;

import android.os.Environment;
import net.avh4.framework.data.ExternalStorage;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AndroidExternalStorage implements ExternalStorage {
    public String getString(String filename) {
        final File dir = Environment.getExternalStorageDirectory();
        final File file = new File(dir, filename);

        try {
            final FileInputStream fis = new FileInputStream(file);
            final String string = IOUtils.toString(fis);
            return string;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
