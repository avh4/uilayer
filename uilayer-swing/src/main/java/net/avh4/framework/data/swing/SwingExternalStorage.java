package net.avh4.framework.data.swing;

import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.File;

import java.util.Arrays;
import java.util.List;

public class SwingExternalStorage implements ExternalStorage {
    private final java.io.File dir;

    public SwingExternalStorage() {
        dir = new java.io.File(".");
    }

    @Override
    public File getFile(String filename) {
        return new SwingFile(dir, filename);
    }

    @Override
    public List<String> getFiles() {
        return Arrays.asList(dir.list());
    }
}
