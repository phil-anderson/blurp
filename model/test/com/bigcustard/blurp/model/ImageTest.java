package com.bigcustard.blurp.model;


import org.junit.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ImageTest {

    @Before
    public void setUp() throws Exception {

        ModelRepository.getInstance().getImages().clear();
    }


    @Test
    public void instantiatedImagesGetAddedToRepository() throws Exception {

        assertThat(ModelRepository.getInstance().getImages().size(), is(0));

        Image testImage = new Image("xyzzy");

        // TODO: Get proper hamcrest matchers into the classpath.
        assertThat(ModelRepository.getInstance().getImages().size(), is(1));
        assertThat(ModelRepository.getInstance().getImages().contains(testImage), is(true));
    }
}
