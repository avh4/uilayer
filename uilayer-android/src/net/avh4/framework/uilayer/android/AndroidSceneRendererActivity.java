package net.avh4.framework.uilayer.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.ImageView;
import net.avh4.framework.uilayer.R;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.AndroidSceneRenderer;

public class AndroidSceneRendererActivity extends Activity {

    private UI ui;
    private AndroidSceneRenderer uiView;

    public AndroidSceneRendererActivity() {
        this(null);
    }

    public AndroidSceneRendererActivity(final UI ui) {
        super();
        ((AndroidUILayerService) UILayer.service).init(this);
        this.ui = ui;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setUI(ui);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            ui.click((int) event.getX(), (int) event.getY());
            uiView.invalidate();
        }
        return true;
    }

    public void setUI(final UI ui) {
        this.ui = ui;
        runOnUiThread(new Runnable() {
            public void run() {
                if (AndroidSceneRendererActivity.this.ui != null) {
                    uiView = new AndroidSceneRenderer(AndroidSceneRendererActivity.this, AndroidSceneRendererActivity.this.ui);
                    setContentView(uiView);
                } else {
                    final ImageView placeholder = new ImageView(
                            AndroidSceneRendererActivity.this);
                    placeholder.setImageResource(R.drawable.icon);
                    setContentView(placeholder);
                }
            }
        });
    }

}
