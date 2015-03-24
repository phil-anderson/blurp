package com.bigcustard.blurp.model;


import org.junit.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ImageTest {

    @Before
    public void setUp() throws Exception {

        Repository.getInstance().getImages().clear();
    }


    @Test
    public void instantiatedImagesGetAddedToRepository() throws Exception {

        assertThat(Repository.getInstance().getImages().size(), is(0));

        Image testImage = new Image("xyzzy");

        // TODO: Get proper hamcrest matchers into the classpath.
        assertThat(Repository.getInstance().getImages().size(), is(1));
        assertThat(Repository.getInstance().getImages().contains(testImage), is(true));
    }
}
