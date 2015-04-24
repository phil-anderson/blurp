package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.constants.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ColourTest {

//    private static Colours colours = Colours.RUNTIME_INSTANCE;

    @Test
    public void equalsReturnsTrueForEquivalentColours() throws Exception {

        Colour colourA = new Colour(1, 1, 0);
        assertThat(colourA.equals(colourA), is(true));

        Colour colourB = new Colour(1, 1, 0);
        assertThat(colourA.equals(colourB), is(true));
    }

    @Test
    public void equalsReturnsFalseForDifferentColours() throws Exception {

        assertThat(Colours.RED.equals(Colours.BLUE), is(false));
    }

    @Test
    public void canMixcolourss() throws Exception {

        assertThat(Colours.RED.mixedWith(Colours.BLUE), is(Colours.PURPLE));
    }

    @Test
    public void canChangeBrightness() throws Exception {

        assertThat(Colours.MAROON.withBrightness(2), is(Colours.RED));
        assertThat(Colours.RED.withBrightness(0.5), is(Colours.MAROON));
    }

    @Test
    public void brightnessChangeIsClamped() throws Exception {

        assertThat(Colours.BLUE.withBrightness(2), is(Colours.BLUE));
    }
}
