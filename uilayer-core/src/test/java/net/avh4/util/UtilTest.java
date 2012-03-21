package net.avh4.util;

import net.avh4.framework.uilayer.Color;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UtilTest {

    @Test
    public void getHashColor_shouldAlwaysHaveFullAlpha() throws Exception {
        assertThat(Color.getAlpha(Util.getHashColor("Body")), is(0xff));
    }
}
