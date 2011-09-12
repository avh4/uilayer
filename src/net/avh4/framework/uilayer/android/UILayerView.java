package net.avh4.framework.uilayer.android;

import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.android.scene.AndroidScene;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class UILayerView extends View {

	private final UI mUi;

	public UILayerView(final Context context, final UI ui) {
		super(context);
		if (ui == null) {
			throw new RuntimeException("UI must not be null");
		}
		mUi = ui;
	}

	@Override
	protected void onDraw(final Canvas canvas) {
		final AndroidScene s = (AndroidScene) mUi.getScene();
		final Paint paint = new Paint();

		if (s == null) {
			paint.setColor(Color.GRAY);
			canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
			paint.setColor(Color.BLACK);
			canvas.drawText("(No scene)", 0, 20, paint);
			return;
		}
		final int height = s.getHeight();
		final int width = s.getWidth();

		paint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, width, height, paint);
	}

}
