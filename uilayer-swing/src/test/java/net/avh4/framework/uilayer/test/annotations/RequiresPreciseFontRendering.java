package net.avh4.framework.uilayer.test.annotations;

import org.junit.internal.AssumptionViolatedException;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Set;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPreciseFontRendering {

    String value() default "";

    class Rule implements MethodRule {
        private static Set<String> knownIncompatibilities = new HashSet<String>();
        private static MethodRule reportJdkVersion = new ReportJdkVersion();

        static {
            knownIncompatibilities.add("1.7.0-u8-b04:1.6.0_26");
            knownIncompatibilities.add("1.7.0-u8-b04:1.6.0_33");
            knownIncompatibilities.add("1.7.0-u8-b04:1.7.0_06");
            knownIncompatibilities.add("1.7.0-u8-b04:1.7.0_07");
        }

        @Override
        public Statement apply(Statement base, FrameworkMethod method, Object target) {
            RequiresPreciseFontRendering annotation = method.getAnnotation(RequiresPreciseFontRendering.class);
            if (annotation == null) {
                return base;
            }
            String requestedVersion = annotation.value();
            String currentVersion = System.getProperty("java.version");
            if (!isCompatible(requestedVersion, currentVersion)) {
                throw new AssumptionViolatedException("Requires font rendering to be identical to JDK " +
                        requestedVersion + " -- the current JDK (" + currentVersion + ") " +
                        "is known to not be compatible");
            }
            return reportJdkVersion.apply(base, method, target);
        }

        private boolean isCompatible(String requestedVersion, String currentVersion) {
            String key = requestedVersion + ":" + currentVersion;
            if (knownIncompatibilities.contains(key)) {
                return false;
            } else {
                return true;
            }
        }
    }

    class ReportJdkVersion implements MethodRule {
        @Override
        public Statement apply(final Statement base, FrameworkMethod method, Object target) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    try {
                        base.evaluate();
                    } catch (Throwable e) {
                        @SuppressWarnings("ChainedMethodCall")
                        StringBuilder sb = new StringBuilder("Reporting JVM info for failed font rendering test:")
                                .append("\n    java.vendor: ").append(System.getProperty("java.vendor"))
                                .append("\n    java.version: ").append(System.getProperty("java.version"))
                                .append("\n    java.specification.version: ").append(System.getProperty("java.specification.version"))
                                .append("\n    java.vm.version: ").append(System.getProperty("java.vm.version"))
                                .append("\n    java.runtime.version: ").append(System.getProperty("java.runtime.version"))
                                .append("\n    os.name: ").append(System.getProperty("os.name"))
                                .append("\n    os.arch: ").append(System.getProperty("os.arch"))
                                .append("\n    os.version: ").append(System.getProperty("os.version"));
                        System.err.println(sb.toString());
                        throw e;
                    }
                }
            };
        }
    }
}
