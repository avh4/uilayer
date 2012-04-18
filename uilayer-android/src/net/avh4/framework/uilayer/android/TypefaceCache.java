package net.avh4.framework.uilayer.android;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

public class TypefaceCache {
    private final Map<String, Typeface> cache = new HashMap<String, Typeface>(3);

    public Typeface get(Context context, String resourceName) {
        Typeface typeface = cache.get(resourceName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), resourceName);
            cache.put(resourceName, typeface);
        }
        return typeface;
    }
}
