package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.model.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ModelRepositoryTest {

    private ModelRepository testCandidate;

    @Mock private Image mockImage;
    @Mock private ImageSprite mockImageSprite;
    @Mock private CommandVisitable mockCommand;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        testCandidate = new ModelRepository();
        BlurpStore.modelRepository = testCandidate;
    }

    @Test
    public void canAddImage() throws Exception {

        // TODO: Mockito assertion for hasSize!?!!
        assertThat(testCandidate.getImages().size(), is(0));
        testCandidate.addImage(mockImage);
        assertThat(testCandidate.getImages().size(), is(1));
    }

    @Test
    public void canRemoveImage() throws Exception {

        testCandidate.addImage(mockImage);
        assertThat(testCandidate.getImages().size(), is(1));
        testCandidate.removeImage(mockImage);
        assertThat(testCandidate.getImages().size(), is(0));
    }

    @Test
    public void canAddImageSprite() throws Exception {

        assertThat(testCandidate.getImageSprites().size(), is(0));
        testCandidate.addImageSprite(mockImageSprite);
        assertThat(testCandidate.getImageSprites().size(), is(1));
    }

    @Test
    public void canRemoveImageSprite() throws Exception {

        testCandidate.addImageSprite(mockImageSprite);
        assertThat(testCandidate.getImageSprites().size(), is(1));
        testCandidate.removeImageSprite(mockImageSprite);
        assertThat(testCandidate.getImageSprites().size(), is(0));
    }


    @Test
    public void canGetImageByName() throws Exception {

        Image image1 = new ImageImpl("abc");
        Image image2 = new ImageImpl("def");
        testCandidate.addImage(image1);
        testCandidate.addImage(image2);
        assertThat(testCandidate.getImages().size(), is(2));
        assertThat(testCandidate.getImage("def"), is(image2));
    }

    @Test
    public void disposeClearsAllLists() throws Exception {

        testCandidate.addImage(mockImage);
        testCandidate.addImageSprite(mockImageSprite);
        testCandidate.dispose();

        assertThat(testCandidate.getImages().size(), is(0));
        assertThat(testCandidate.getImageSprites().size(), is(0));
    }
}
