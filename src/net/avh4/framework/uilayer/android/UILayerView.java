package net.avh4.framework.uilayer.android;

import net.avh4.framework.uilayer.UI;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class UILayerView extends View {

	private final UI mUi;

	public UILayerView(final Context context, final UI ui) {
		super(context);
		mUi = ui;
	}

	@Override
	protected void onDraw(final Canvas canvas) {
		final Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawRect(0, 0, 100, 200, paint);
	}

}
