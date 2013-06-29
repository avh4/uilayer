package net.avh4.framework.uilayer.layout;

import net.avh4.math.geometry.Rect;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class VerticalLayoutTest {
    @Test
    public void shouldSizeAspectRatioConstrainedItem() {
        VerticalLayout<String> subject = new VerticalLayout<String>()
                .addAspectRatio("Chart", 4.0 / 3);
        final BoundsTransformation transformation = subject.get("Chart");
        assertThat(transformation.apply(Rect.ofSize(100, 200)), is(Rect.fromTopLeft(0, 0, 100, 75)));
        assertThat(transformation.apply(Rect.ofSize(1000, 2000)), is(Rect.fromTopLeft(0, 0, 1000, 750)));
    }

    @Test
    public void shouldSizeFixedHeightConstrainedItem() {
        VerticalLayout<String> subject = new VerticalLayout<String>()
                .addFixedHeight("Chart", 200);
        final BoundsTransformation transformation = subject.get("Chart");
        assertThat(transformation.apply(Rect.ofSize(100, 200)), is(Rect.fromTopLeft(0, 0, 100, 200)));
        assertThat(transformation.apply(Rect.ofSize(1000, 2000)), is(Rect.fromTopLeft(0, 0, 1000, 200)));
    }

    @Test
    public void shouldPlaceSecondItemBelowTheFirst_withAspectRatioItem() {
        VerticalLayout<String> subject = new VerticalLayout<String>()
                .addAspectRatio("Chart", 4.0 / 3)
                .addAspectRatio("Chart2", 4.0 / 3);
        final BoundsTransformation transformation = subject.get("Chart2");
        assertThat(transformation.apply(Rect.ofSize(100, 200)), is(Rect.fromTopLeft(0, 75, 100, 75)));
        assertThat(transformation.apply(Rect.ofSize(1000, 2000)), is(Rect.fromTopLeft(0, 750, 1000, 750)));
    }

    @Test
    public void shouldPlaceSecondItemBelowTheFirst_withFixedSizeItem() {
        VerticalLayout<String> subject = new VerticalLayout<String>()
                .addAspectRatio("Chart", 4.0 / 3)
                .addFixedHeight("Button", 100);
        final BoundsTransformation transformation = subject.get("Button");
        assertThat(transformation.apply(Rect.ofSize(100, 200)), is(Rect.fromTopLeft(0, 75, 100, 100)));
        assertThat(transformation.apply(Rect.ofSize(1000, 2000)), is(Rect.fromTopLeft(0, 750, 1000, 100)));
    }
}
