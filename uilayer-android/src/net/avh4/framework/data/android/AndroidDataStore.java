package net.avh4.framework.data.android;

import android.content.Context;
import android.content.SharedPreferences;
import net.avh4.framework.data.DataStore;

public class AndroidDataStore implements DataStore {

    private final SharedPreferences sharedPreferences;

    public AndroidDataStore(Context context) {
        sharedPreferences = context.getSharedPreferences("DataStore", Context.MODE_PRIVATE);
    }

    public void put(String key, String value) {
        sharedPreferences.edit().putString(key, value).commit();
    }

    public String get(String key) {
        return sharedPreferences.getString(key, null);
    }
}
