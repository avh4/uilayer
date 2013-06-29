package net.avh4.framework.uilayer.layout;

import net.avh4.math.geometry.Rect;
import org.pcollections.HashPMap;
import org.pcollections.HashTreePMap;
import org.pcollections.PMap;

public class VerticalLayout<T> implements Layout<T> {

    private final HashPMap<T, SizingStrategy> strategies;

    public VerticalLayout() {
        strategies = HashTreePMap.empty();
    }

    private VerticalLayout(HashPMap<T, SizingStrategy> strategies) {
        this.strategies = strategies;
    }

    private interface SizingStrategy {
        double calculateMinY(Rect bounds);

        double calculateHeight(Rect bounds);
    }

    public abstract static class SizingStrategyBase implements SizingStrategy {
        protected final PMap<?, SizingStrategy> predecessors;

        public SizingStrategyBase(PMap<?, SizingStrategy> predecessors) {
            this.predecessors = predecessors;
        }

        @Override
        public double calculateMinY(Rect bounds) {
            double sum = 0;
            for (SizingStrategy s : predecessors.values()) {
                sum += s.calculateHeight(bounds);
            }
            return sum;
        }

        @Override
        public abstract double calculateHeight(Rect bounds);
    }

    private static class AspectRatioSizingStrategy extends SizingStrategyBase {
        private final double aspectRatio;

        private AspectRatioSizingStrategy(PMap<?, SizingStrategy> predecessors, double aspectRatio) {
            super(predecessors);
            this.aspectRatio = aspectRatio;
        }

        @Override
        public double calculateHeight(Rect bounds) {
            return bounds.width() / aspectRatio;
        }
    }

    private static class FixedHeightSizingStrategy extends SizingStrategyBase {
        private final double height;

        private FixedHeightSizingStrategy(PMap<?, SizingStrategy> predecessors, double height) {
            super(predecessors);
            this.height = height;
        }

        @Override
        public double calculateHeight(Rect bounds) {
            return height;
        }
    }

    @Override
    public BoundsTransformation get(final T key) {
        return new BoundsTransformation() {
            @Override
            public Rect apply(Rect bounds) {
                SizingStrategy strategy = strategies.get(key);
                final double height = strategy.calculateHeight(bounds);
                final double minY = strategy.calculateMinY(bounds);
                return Rect.fromTopLeft(0, minY, bounds.width(), height);
            }
        };
    }

    public VerticalLayout<T> addAspectRatio(T key, double aspectRatio) {
        final HashPMap<T, SizingStrategy> newStrategies
                = strategies.plus(key, new AspectRatioSizingStrategy(strategies, aspectRatio));
        return new VerticalLayout<T>(newStrategies);
    }

    public VerticalLayout<T> addFixedHeight(T key, double height) {
        final HashPMap<T, SizingStrategy> newStrategies
                = strategies.plus(key, new FixedHeightSizingStrategy(strategies, height));
        return new VerticalLayout<T>(newStrategies);
    }
}
