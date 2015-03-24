package com.bigcustard.blurp.model;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ColourTest {

    @Test
    public void equalsReturnsTrueForEquivalentColours() throws Exception {

        Colour colourA = new Colour(1, 1, 0);
        assertThat(colourA.equals(colourA), is(true));

        Colour colourB = new Colour(1, 1, 0);
        assertThat(colourA.equals(colourB), is(true));
    }

    @Test
    public void equalsReturnsFalseForDifferentColours() throws Exception {

        assertThat(Colour.RED.equals(Colour.BLUE), is(false));
    }

    @Test
    public void canMixColours() throws Exception {

        assertThat(Colour.RED.mixedWith(Colour.BLUE), is(Colour.PURPLE));
    }

    @Test
    public void canChangeBrightness() throws Exception {

        assertThat(Colour.DARK_BLUE.withBrightness(2), is(Colour.BLUE));
    }
}
