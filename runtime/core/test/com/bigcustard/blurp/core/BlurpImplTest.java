package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BlurpImplTest {

    @Mock private ApiModelRepository mockModelRepository;
    @Mock private Screen mockScreen;
    @Mock private Blurpifier mockBlurpifier;
    @Mock private Image mockImage;

    private BlurpImpl testCandidate;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        testCandidate = new BlurpImpl(mockModelRepository, mockScreen, mockBlurpifier);
    }

    @Test
    public void blurpifyCallsBlurpifier() {

        testCandidate.blurpify();
        Mockito.verify(mockBlurpifier).blurpify();
    }

    @Test
    public void creatingImagesAddsThemToModelRepository() {

        Image image = testCandidate.image("abc");
        Mockito.verify(mockModelRepository).addImage(image);
    }

    @Test
    public void creatingImageSpritesAddsThemToModelRepository() {

        ImageSprite sprite1 = testCandidate.imageSprite("abc");
        ImageSprite sprite2 = testCandidate.imageSprite("abc", 0.0, 0.0);
        ImageSprite sprite3 = testCandidate.imageSprite(mockImage);
        ImageSprite sprite4 = testCandidate.imageSprite(mockImage, 0.0, 0.0);
        Mockito.verify(mockModelRepository).addImageSprite(sprite1);
        Mockito.verify(mockModelRepository).addImageSprite(sprite2);
        Mockito.verify(mockModelRepository).addImageSprite(sprite3);
        Mockito.verify(mockModelRepository).addImageSprite(sprite4);
    }

    @Test
    public void coloursHaveCorrectRedGreenAndBlueComponents() {

        assertThat(testCandidate.colour(1.0, 0.8431373, 0.0), is(Colours.INSTANCE.gold));
    }
}
