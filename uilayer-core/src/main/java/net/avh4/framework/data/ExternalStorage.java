package net.avh4.framework.data;

import java.util.List;

public interface ExternalStorage {

    File getFile(String filename);

    List<String> getFiles();
}
