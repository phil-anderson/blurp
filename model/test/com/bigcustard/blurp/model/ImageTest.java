package com.bigcustard.blurp.model;


import com.bigcustard.blurp.core.*;
import org.junit.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class ImageTest {

    @Mock IModelRepository modelRepository;
    @Mock Canvas mockCanvas;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        MSS.dispose();
        MSS.setInstances(modelRepository, mockCanvas);
    }

    @Test
    public void instantiatedImagesGetAddedToRepository() throws Exception {

        Image testImage = new Image("xyzzy");

        verify(modelRepository).addImage(testImage);
    }
}
