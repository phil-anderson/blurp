package com.bigcustard.blurp.model;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ImageSpriteTest {

    @Before
    public void setUp() throws Exception {

        Repository.getInstance().getImageSprites().clear();
    }

    @Test
    public void instantiatedImageSpritesGetAddedToRepository() throws Exception {

        assertThat(Repository.getInstance().getImageSprites().size(), is(0));

        ImageSprite testImageSprite = new ImageSprite("xyzzy");

        // TODO: Get proper hamcrest matchers into the classpath.
        assertThat(Repository.getInstance().getImageSprites().size(), is(1));
        assertThat(Repository.getInstance().getImageSprites().contains(testImageSprite), is(true));
    }

    @Test
    public void flipsReverseScale() throws Exception {

        ImageSprite testImageSprite = new ImageSprite("xyzzy");
        testImageSprite.scaleX = 0.5;
        testImageSprite.scaleY = 1.5;

        assertScaleXY(testImageSprite, 0.5, 1.5);

        testImageSprite.flipX();
        assertScaleXY(testImageSprite, -0.5, 1.5);

        testImageSprite.flipY();
        assertScaleXY(testImageSprite, -0.5, -1.5);
    }

    @Test
    public void setsBoundImageProperty() throws Exception {

        ImageSprite boundImageSprite = new ImageSprite("xyzzy");
        assertThat(boundImageSprite.isBoundImage(), is(true));

        Image testImage = new Image("xyzzy");
        ImageSprite unboundImageSprite = new ImageSprite(testImage);
        assertThat(unboundImageSprite.isBoundImage(), is(false));
    }

    private void assertScaleXY(ImageSprite imageSprite, double expectedX, double expectedY) {

        assertThat(imageSprite.scaleX, is(expectedX));
        assertThat(imageSprite.scaleY, is(expectedY));
    }
}
