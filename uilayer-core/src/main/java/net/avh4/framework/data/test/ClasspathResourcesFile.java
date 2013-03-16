package net.avh4.framework.data.test;

import net.avh4.framework.data.ExternalStorageException;
import net.avh4.framework.data.File;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class ClasspathResourcesFile implements File {
    private final String filename;
    private final String root;
    private String writtenContents = null;

    public ClasspathResourcesFile(String root, String filename) {
        this.root = root;
        this.filename = filename;
    }

    @Override
    public String getName() {
        return filename;
    }

    @Override
    public String getContents() {
        if (writtenContents != null) {
            return writtenContents;
        }
        return getStringFromClasspath();
    }

    @Override
    public void writeContents(String data) throws ExternalStorageException {
        writtenContents = data;
    }

    public String getStringFromClasspath() {
        final String resourcePath = root + "/" + filename;
        final InputStream is = ClassLoader.getSystemResourceAsStream(resourcePath);
        if (is == null) {
            throw new RuntimeException("Resource " + resourcePath + " does not exist");
        }
        try {
            return readString(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readString(InputStream is) throws IOException {
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

    public boolean exists() {
        final String resourcePath = root + "/" + filename;
        final InputStream is = ClassLoader.getSystemResourceAsStream(resourcePath);
        return is != null;
    }
}
