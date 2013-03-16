package net.avh4.framework.data.swing;

import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.ExternalStorageException;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SwingExternalStorage implements ExternalStorage {
    private final File dir;

    public SwingExternalStorage() {
        dir = new File(".");
    }

    public String getString(String filename) {
        final File file = new File(dir, filename);

        try {
            final FileInputStream fis = new FileInputStream(file);
            return IOUtils.toString(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public List<String> getFiles() {
        return Arrays.asList(dir.list());
    }

    @Override
    public void writeFile(String filename, String data) throws ExternalStorageException {
        final File file = new File(dir, filename);
        try {
            final FileOutputStream fos = new FileOutputStream(file);
            IOUtils.write(data, fos);
        } catch (FileNotFoundException e) {
            throw new ExternalStorageException("Cannot write file: " + filename, e);
        } catch (IOException e) {
            throw new ExternalStorageException("Cannot write file: " + filename, e);
        }
    }
}
