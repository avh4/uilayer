package net.avh4.util.imagecomparison.android;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

import android.content.Context;

/**
 * A class containing factory methods for hamcrest matchers contained in
 * {@code net.avh4.test}.
 * 
 * @author avh4
 */
public class Matchers {

	@Factory
	public static Matcher<Object> looksLike(final Context context,
			final String string) {
		return new LooksLikeMatcher(context, string);
	}

	@Factory
	public static Matcher<Object> isApproved(final Context context) {
		final StackTraceElement trace = StackUtils
				.getCallingTestMethodStackTraceElement();
		final String methodName = trace.getMethodName();
		final String className = StackUtils.getClass(trace).getSimpleName();
		return looksLike(context,
				String.format("%s.%s.png", className, methodName));
	}
}
