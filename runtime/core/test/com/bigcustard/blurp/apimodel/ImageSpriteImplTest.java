package com.bigcustard.blurp.apimodel;

import com.bigcustard.blurp.core.*;
import com.bigcustard.blurp.model.*;
import org.junit.*;
import org.mockito.*;

public class ImageSpriteImplTest {

    private ImageSprite testCandidate;

    @Mock private RuntimeRepository mockRuntimeRepository;
    @Mock private ModelRepository mockModelRepository;

    @Before
    public void setUp() throws Exception {

        BlurpStore.modelRepository = mockModelRepository;
        BlurpStore.runtimeRepository = mockRuntimeRepository;
        MockitoAnnotations.initMocks(this);
        ImageImpl testImage = new ImageImpl("abc");
        testCandidate = new ImageSpriteImpl(testImage, .0, .0);
    }

    @Test
    public void removeRemovesImageSpriteFromModelRepository() {

        testCandidate.remove();
        Mockito.verify(mockModelRepository).removeImageSprite(testCandidate);
    }
}
