package net.avh4.framework.data.test;

import net.avh4.framework.data.ExternalStorage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * This can be used for unit testing to simulate files on an external storage device.
 */
public class ClasspathResourcesExternalStorage implements ExternalStorage {
    private final String root;
    private final ArrayList<String> allowedFiles = new ArrayList<String>();

    public ClasspathResourcesExternalStorage(String root) {
        this.root = root;
    }

    public String getString(String filename) {
        final String resourcePath = root + "/" + filename;
        final InputStream is = ClassLoader.getSystemResourceAsStream(resourcePath);
        if (is == null) {
            throw new RuntimeException("Resource " + resourcePath + " does not exist");
        }
        try {
            final String content = readString(is);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getFiles() {
        return allowedFiles;
    }

    private String readString(InputStream is) throws IOException {
        final char[] buffer = new char[0x10000];
        final StringBuilder out = new StringBuilder();
        final Reader in = new InputStreamReader(is, "UTF-8");
        int read;
        do {
            read = in.read(buffer, 0, buffer.length);
            if (read > 0) {
                out.append(buffer, 0, read);
            }
        } while (read >= 0);
        return out.toString();
    }

    public void allowFile(String filename) {
        allowedFiles.add(filename);
    }
}
