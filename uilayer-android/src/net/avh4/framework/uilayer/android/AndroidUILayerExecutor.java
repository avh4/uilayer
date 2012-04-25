package net.avh4.framework.uilayer.android;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AndroidUILayerExecutor extends ScheduledThreadPoolExecutor {

	public static final int CORE_POOL_SIZE = 1;
	private final AndroidSceneRendererActivity activity;

	public AndroidUILayerExecutor(AndroidSceneRendererActivity activity) {
		super(CORE_POOL_SIZE);
		this.activity = activity;
	}

	@Override
	public ScheduledFuture<?> schedule(final Runnable command, long delay,
			TimeUnit unit) {
		return super
				.schedule(new InvalidateAfterRunnable(command), delay, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleAtFixedRate(final Runnable command,
			long initialDelay, long period, TimeUnit unit) {
		return super.scheduleAtFixedRate(new InvalidateAfterRunnable(command),
				initialDelay, period, unit);
	}

	@Override
	public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
			long initialDelay, long delay, TimeUnit unit) {
		return super
				.scheduleWithFixedDelay(new InvalidateAfterRunnable(command),
						initialDelay, delay, unit);
	}

	private class InvalidateAfterRunnable implements Runnable {
		private final Runnable command;

		public InvalidateAfterRunnable(Runnable command) {
			this.command = command;
		}

		@Override
		public void run() {
			command.run();
			activity.getUiView().postInvalidate();
		}
	}
}
