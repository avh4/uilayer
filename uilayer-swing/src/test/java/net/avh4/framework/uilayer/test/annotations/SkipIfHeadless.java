package net.avh4.framework.uilayer.test.annotations;

import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SkipIfHeadless {
    class Rule implements MethodRule {
        @Override
        public Statement apply(Statement base, FrameworkMethod method, Object target) {
            if (method.getAnnotation(SkipIfHeadless.class) != null
                    && java.awt.GraphicsEnvironment.isHeadless()) {
                throw new AssumptionViolatedException("Cannot run on a headless GraphicsEnvironment");
            } else {
                return base;
            }
        }
    }
}
