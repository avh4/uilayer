package net.avh4.framework.uilayer.android;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.mockito.InOrder;
import org.mockito.Mockito;
import android.test.InstrumentationTestCase;
import android.view.View;

public class AndroidUILayerExecutorTest extends InstrumentationTestCase {

	View uiView;
	private AndroidUILayerExecutor subject;
	private Runnable runnable;

	public void setUp() throws Exception {
		getInstrumentation().getTargetContext().getCacheDir();

		runnable = Mockito.mock(Runnable.class);
		uiView = Mockito.mock(View.class);
		final AndroidSceneRendererActivity activity = Mockito
				.mock(AndroidSceneRendererActivity.class);
		Mockito.stub(activity.getUiView()).toReturn(uiView);

		subject = new AndroidUILayerExecutor(activity);
	}

	@Override
	protected void tearDown() throws Exception {
		subject.shutdownNow();
		super.tearDown();
	}

	public void testSchedule_shouldInvalidateCurrentUiView() throws Exception {
		final ScheduledFuture<?> future = subject
				.schedule(runnable, 10, TimeUnit.MILLISECONDS);
		future.get();

		Mockito.verify(uiView).postInvalidate();
	}

	public void testSchedule_shouldInvokeCommand() throws Exception {
		final ScheduledFuture<?> future = subject
				.schedule(runnable, 10, TimeUnit.MILLISECONDS);
		future.get();

		Mockito.verify(runnable).run();
	}

	public void testSchedule_shouldInvalidateViewAfterInvokingCommand()
			throws Exception {
		final ScheduledFuture<?> future = subject
				.schedule(runnable, 10, TimeUnit.MILLISECONDS);
		future.get();

		final InOrder inOrder = Mockito.inOrder(runnable, uiView);
		inOrder.verify(runnable).run();
		inOrder.verify(uiView).postInvalidate();
	}

	public void testScheduleAtFixedRate_shouldInvalidateCurrentUiView()
			throws Exception {
		subject.scheduleAtFixedRate(runnable, 10, 500000,
				TimeUnit.MILLISECONDS);
		Thread.sleep(20);

		Mockito.verify(uiView).postInvalidate();
	}

	public void testScheduleWithFixedDelay_shouldInvalidateCurrentUiView()
			throws Exception {
		subject.scheduleWithFixedDelay(runnable, 10, 500000,
				TimeUnit.MILLISECONDS);
		Thread.sleep(20);

		Mockito.verify(uiView).postInvalidate();
	}
}
