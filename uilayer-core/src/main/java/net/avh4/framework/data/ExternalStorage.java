package net.avh4.framework.data;

import java.util.List;

public interface ExternalStorage {

    String getString(String filename);

    List<String> getFiles();

    void writeFile(String filename, String data) throws ExternalStorageException;
}
