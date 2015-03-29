package com.bigcustard.blurp.model;


import com.bigcustard.blurp.core.*;
import org.junit.*;
import org.mockito.*;

import static org.mockito.Mockito.*;

public class ImageTest {

    @Mock
    IModelRepository modelRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        MSS.dispose();
        MSS.setInstances(modelRepository);
    }

    @Test
    public void instantiatedImagesGetAddedToRepository() throws Exception {

        Image testImage = new Image("xyzzy");

        verify(modelRepository).addImage(testImage);
    }
}
