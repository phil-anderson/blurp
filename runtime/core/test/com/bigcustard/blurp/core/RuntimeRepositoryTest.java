//package com.bigcustard.blurp.core;
//
//import com.bigcustard.blurp.model.*;
//import com.bigcustard.blurp.testutils.*;
//import org.junit.*;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
//
//public class RuntimeRepositoryTest extends LibGdxTest {
//
//
//    private RuntimeRepository testCandidate;
//    private Image image;
//    private ImageSprite imageSprite;
//
//    @Before
//    public void setUp() throws Exception {
//
//        BlurpObjectProvider blurpObjectProvider = new BlurpObjectProviderForTests();
//        blurpObjectProvider.initialiseSingletons();
//
//        testCandidate = RSS.getRuntimeRepository();
//
//        // Instantiate each type of object
//        image = new Image("star.png");
//        imageSprite = new ImageSprite(image);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//        RSS.dispose();
//    }
//
//    @Test
//    public void syncCallsSyncAllForEachTypeOfObject() throws Exception {
//
//        testCandidate.syncWithModelRepository(1);
//        assertThat(testCandidate.getImage(image), notNullValue());
//        assertThat(testCandidate.getImageSprite(imageSprite), notNullValue());
//    }
//}
