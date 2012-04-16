package net.avh4.framework.data.android;

import android.os.Environment;
import net.avh4.framework.data.ExternalStorage;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AndroidExternalStorage implements ExternalStorage {

    private final File dir;

    public AndroidExternalStorage() {
        dir = Environment.getExternalStorageDirectory();
    }

    public String getString(String filename) {
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

    @Override
    public List<String> getFiles() {
        return Arrays.asList(dir.list());
    }
}
