package com.bigcustard.blurp.model;

import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ColourTest {

    private static Colours colours = Colours.INSTANCE;

    @Test
    public void equalsReturnsTrueForEquivalentColours() throws Exception {

        Colour colourA = new Colour(1, 1, 0);
        assertThat(colourA.equals(colourA), is(true));

        Colour colourB = new Colour(1, 1, 0);
        assertThat(colourA.equals(colourB), is(true));
    }

    @Test
    public void equalsReturnsFalseForDifferentColours() throws Exception {

        assertThat(colours.red.equals(colours.blue), is(false));
    }

    @Test
    public void canMixcolourss() throws Exception {

        assertThat(colours.red.mixedWith(colours.blue), is(colours.purple));
    }

    @Test
    public void canChangeBrightness() throws Exception {

        assertThat(colours.maroon.withBrightness(2), is(colours.red));
        assertThat(colours.red.withBrightness(0.5), is(colours.maroon));
    }

    @Test
    public void brightnessChangeIsClamped() throws Exception {

        assertThat(colours.blue.withBrightness(2), is(colours.blue));
    }
}
