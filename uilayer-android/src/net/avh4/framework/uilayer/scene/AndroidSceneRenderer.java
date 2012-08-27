package net.avh4.framework.uilayer.scene;

import net.avh4.framework.uilayer.SceneCreator;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class AndroidSceneRenderer extends View {
	private final AndroidGraphicsOperations graphicsOperations;
	private final AndroidFontMetricsService fontMetricsService;
	private final SceneRenderer renderer;

	public AndroidSceneRenderer(final Context context,
			final SceneCreator creator) {
		super(context);
		if (creator == null) {
			throw new RuntimeException("SceneCreator must not be null");
		}
		fontMetricsService = new AndroidFontMetricsService(context);
		graphicsOperations = new AndroidGraphicsOperations(context);
		renderer = new SceneRenderer(creator);
	}

	@Override
	protected void onDraw(final Canvas canvas) {
		synchronized (graphicsOperations) {
			graphicsOperations.setCanvas(canvas);
			renderer.render(graphicsOperations, fontMetricsService);
			graphicsOperations.setCanvas(null);
		}
	}
}
