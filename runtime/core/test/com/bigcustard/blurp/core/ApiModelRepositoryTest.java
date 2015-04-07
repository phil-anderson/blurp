package com.bigcustard.blurp.core;

import com.bigcustard.blurp.apimodel.*;
import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.commands.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ApiModelRepositoryTest {

    private ApiModelRepository testCandidate;

    @Mock private Image mockImage;
    @Mock private ImageSprite mockImageSprite;
    @Mock private CommandVisitable mockCommand;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        testCandidate = new ApiModelRepository();
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
    public void canRegisterCommand() throws Exception {

        assertThat(testCandidate.getCommandRequests().size(), is(0));
        testCandidate.registerCommand(mockCommand);
        assertThat(testCandidate.getCommandRequests().size(), is(1));
    }

    @Test
    public void commandExecutionCompleteClearsCommandRequests() throws Exception {

        testCandidate.registerCommand(mockCommand);
        assertThat(testCandidate.getCommandRequests().size(), is(1));
        testCandidate.commandExecutionComplete();
        assertThat(testCandidate.getCommandRequests().size(), is(0));
    }

    @Test
    public void canGetImageByName() throws Exception {

        Image image1 = new ImageImpl("abc", testCandidate);
        Image image2 = new ImageImpl("def", testCandidate);
        testCandidate.addImage(image1);
        testCandidate.addImage(image2);
        assertThat(testCandidate.getImages().size(), is(2));
        assertThat(testCandidate.getImage("def"), is(image2));
    }

    @Test
    public void disposeClearsAllLists() throws Exception {

        testCandidate.addImage(mockImage);
        testCandidate.addImageSprite(mockImageSprite);
        testCandidate.registerCommand(mockCommand);
        testCandidate.dispose();

        assertThat(testCandidate.getImages().size(), is(0));
        assertThat(testCandidate.getImageSprites().size(), is(0));
        assertThat(testCandidate.getCommandRequests().size(), is(0));
    }
}
