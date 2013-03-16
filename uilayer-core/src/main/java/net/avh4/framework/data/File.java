package net.avh4.framework.data;

public interface File {
    String getContents();

    void writeContents(String data) throws ExternalStorageException;
}
