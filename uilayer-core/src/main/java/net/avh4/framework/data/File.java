package net.avh4.framework.data;

public interface File {
    String getName();

    String getContents();

    void writeContents(String data) throws ExternalStorageException;
}
