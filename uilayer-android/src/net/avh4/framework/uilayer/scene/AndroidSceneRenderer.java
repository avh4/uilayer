package net.avh4.framework.uilayer.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import net.avh4.framework.uilayer.SceneCreator;

public class AndroidSceneRenderer extends View {
    private final AndroidGraphicsOperations graphicsOperations;
    private final SceneRenderer renderer;

    public AndroidSceneRenderer(final Context context, final SceneCreator creator) {
        super(context);
        if (creator == null) {
            throw new RuntimeException("SceneCreator must not be null");
        }
        final AndroidFontMetricsService fm = new AndroidFontMetricsService(context);
        graphicsOperations = new AndroidGraphicsOperations(context);
        renderer = new SceneRenderer(creator, graphicsOperations, fm);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        synchronized (graphicsOperations) {
            graphicsOperations.setCanvas(canvas);
            renderer.render();
            graphicsOperations.setCanvas(null);
        }
    }
}
