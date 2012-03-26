package net.avh4.framework.data;

public interface DataStore {
    void put(String key, String value);

    String get(String key);
}
