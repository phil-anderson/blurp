package com.bigcustard.blurp.model;

import com.bigcustard.blurp.core.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

public class ImageSpriteTest {

    @Mock
    IModelRepository modelRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        MSS.dispose();
        MSS.setInstances(modelRepository);
    }

    @Test
    public void instantiatedImageSpritesGetAddedToRepository() throws Exception {

        ImageSprite testImageSprite = new ImageSprite("xyzzy", 100, 100);
        verify(modelRepository).addImageSprite(testImageSprite);
    }

    @Test
    public void flipsReverseScale() throws Exception {

        ImageSprite testImageSprite = new ImageSprite("xyzzy", 100, 100);
        testImageSprite.scaleX = 0.5;
        testImageSprite.scaleY = 1.5;

        assertScaleXY(testImageSprite, 0.5, 1.5);

        testImageSprite.flipX();
        assertScaleXY(testImageSprite, -0.5, 1.5);

        testImageSprite.flipY();
        assertScaleXY(testImageSprite, -0.5, -1.5);
    }

    private void assertScaleXY(ImageSprite imageSprite, double expectedX, double expectedY) {

        assertThat(imageSprite.scaleX, is(expectedX));
        assertThat(imageSprite.scaleY, is(expectedY));
    }
}
