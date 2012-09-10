package net.avh4.framework.uilayer.android;

import android.os.Bundle;
import net.avh4.demo.uilayer.DemoGame;

public class DemoGameActivity extends AndroidSceneRendererActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI(new DemoGame());
    }
}
