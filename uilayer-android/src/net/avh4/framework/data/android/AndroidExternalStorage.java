package net.avh4.framework.data.android;

import android.os.Environment;
import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.ExternalStorageException;
import net.avh4.framework.data.File;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AndroidExternalStorage implements ExternalStorage {

    private final java.io.File dir;

    public AndroidExternalStorage() {
        dir = Environment.getExternalStorageDirectory();
    }

    @Override
    public File getFile(String filename) {
        return new FileImpl(filename);
    }

    @Override
    public List<String> getFiles() {
        return Arrays.asList(dir.list());
    }

    private class FileImpl implements File {
        private final java.io.File file;
        private final String filename;

        public FileImpl(String filename) {
            this.filename = filename;
            this.file = new java.io.File(dir, filename);
        }

        @Override
        public String getName() {
            return filename;
        }

        @Override
        public String getContents() {
            try {
                final FileInputStream fis = new FileInputStream(file);
                return IOUtils.toString(fis);
            } catch (FileNotFoundException e) {
                return null;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public void writeContents(String data) throws ExternalStorageException {
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
}
