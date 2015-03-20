package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class RuntimeRepositoryTest {

    private RuntimeRepository testCandidate = RuntimeRepository.getInstance();
    private Image image;
    private ImageSprite imageSprite;

    @Before
    public void setUp() throws Exception {

        // Instantiate an instance of each type of object
        image = new Image("TestImage.png");
        imageSprite = new ImageSprite(image);
    }

    @Test
    public void syncCallsSyncAllForEachTypeOfObject() throws Exception {

        testCandidate.syncWithModelRepository();
        assertThat(testCandidate.getImage(image), notNullValue());
        assertThat(testCandidate.getImageSprite(imageSprite), notNullValue());
    }
}
