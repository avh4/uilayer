package net.avh4.framework.uilayer.android;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneElement;
import net.avh4.framework.uilayer.scene.SceneRenderer;

public class AndroidSceneRenderer extends View {
    private final AndroidGraphicsOperations graphicsOperations;
    private final AndroidFontMetricsService fontMetricsService;
    private final GraphicsOperationsRenderer renderer;

    public AndroidSceneRenderer(final Context context, final SceneCreator creator) {
        this(context, new AndroidGraphicsOperations(context), new SceneRenderer(creator));
    }

    public AndroidSceneRenderer(final Context context, final Scene scene) {
        this(context, new AndroidGraphicsOperations(context), new SceneRenderer(scene));
    }

    public AndroidSceneRenderer(final Context context, final SceneElement element) {
        this(context, new AndroidGraphicsOperations(context), new SceneRenderer(element));
    }

    public AndroidSceneRenderer(final Context context,
                                AndroidGraphicsOperations g, GraphicsOperationsRenderer sceneRenderer) {
        super(context);
        fontMetricsService = new AndroidFontMetricsService(context);
        graphicsOperations = g;
        renderer = sceneRenderer;
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        synchronized (graphicsOperations) {
            graphicsOperations.setCanvas(canvas);
            renderer.render(getWidth(), getHeight(), graphicsOperations, fontMetricsService);
            graphicsOperations.setCanvas(null);
        }
    }
}
