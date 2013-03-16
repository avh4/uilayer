package net.avh4.framework.data.test;

import net.avh4.framework.data.ExternalStorage;
import net.avh4.framework.data.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This can be used for unit testing to simulate files on an external storage device.
 */
public class ClasspathResourcesExternalStorage implements ExternalStorage {
    private final String root;
    private final ArrayList<String> allowedFiles = new ArrayList<String>();
    private final HashMap<String, File> files = new HashMap<String, File>();

    public ClasspathResourcesExternalStorage(String root) {
        this.root = root;
    }

    @Override
    public List<String> getFiles() {
        return allowedFiles;
    }

    public void allowFile(String filename) {
        allowedFiles.add(filename);
    }

    @Override
    public File getFile(String filename) {
        if (!files.containsKey(filename)) {
            ClasspathResourcesFile file = new ClasspathResourcesFile(root, filename);
            if (file.exists() && !allowedFiles.contains(filename)) {
                return null;
            }
            files.put(filename, file);
        }
        return files.get(filename);
    }
}
