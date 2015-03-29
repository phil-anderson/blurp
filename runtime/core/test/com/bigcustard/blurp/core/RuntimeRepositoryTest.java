package com.bigcustard.blurp.core;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.testutils.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@Ignore // I can't get these tests to run.
public class RuntimeRepositoryTest extends LibGdxTest {

    private RuntimeRepository testCandidate;
    private Image image;
    private ImageSprite imageSprite;

    @Before
    public void setUp() throws Exception {

        ModelRepository modelRepository = new ModelRepository(new Canvas(800, 600));
        MSS.setInstances(modelRepository);

        testCandidate = new RuntimeRepository(modelRepository);

        // Instantiate each type of object
        image = new Image("star.png");
        imageSprite = new ImageSprite(image);
    }

    @After
    public void tearDown() throws Exception {

        SF.dispose();
    }

    @Test
    public void syncCallsSyncAllForEachTypeOfObject() throws Exception {

        testCandidate.syncWithModelRepository();
        assertThat(testCandidate.getImage(image), notNullValue());
        assertThat(testCandidate.getImageSprite(imageSprite), notNullValue());
    }
}
