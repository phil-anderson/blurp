package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.bootstrap.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.constants.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class SystemImplTest {

    @Mock private RuntimeRepository mockRuntimeRepository;
    @Mock private ModelRepository mockModelRepository;
    @Mock private ScreenImpl mockScreen;
    @Mock private Blurpifier mockBlurpifier;

    @Mock private Image mockImage;

    private SystemImpl testCandidate;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        BlurpStore.runtimeRepository = mockRuntimeRepository;
        BlurpStore.modelRepository = mockModelRepository;
        BlurpStore.modelScreen = mockScreen;
        BlurpStore.blurpifier = mockBlurpifier;
        BlurpStore.configuration = new BlurpConfiguration(100, 100);
        testCandidate = new SystemImpl();
    }

    @Test
    public void creatingImagesAddsThemToModelRepository() {

        Image image = testCandidate.loadImage("abc");
        Mockito.verify(mockModelRepository).addImage(image);
    }

    @Test
    public void creatingImageSpritesAddsThemToModelRepository() {

        ImageSprite sprite1 = testCandidate.createImageSprite("abc");
        ImageSprite sprite2 = testCandidate.createImageSprite(mockImage);
        Mockito.verify(mockModelRepository).addImageSprite(sprite1);
        Mockito.verify(mockModelRepository).addImageSprite(sprite2);
    }

    @Test
    public void coloursHaveCorrectRedGreenAndBlueComponents() {

        assertThat(testCandidate.createColour(1.0, 0.8431373, 0.0), is(Colours.GOLD));
    }
}
