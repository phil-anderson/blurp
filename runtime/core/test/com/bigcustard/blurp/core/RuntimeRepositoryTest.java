//package com.bigcustard.blurp.core;
//
//import com.bigcustard.blurp.apimodel.*;
//import com.bigcustard.blurp.model.*;
//import com.bigcustard.blurp.testutils.*;
//import org.junit.*;
//import org.mockito.*;
//
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.*;
//
//public class RuntimeRepositoryTest extends LibGdxTest {
//
//    @Mock private BlurpObjectProvider mockBlurpObjectProvider;
//
//    RuntimeRepository testCandidate;
//
//    private Image image2;
//    private Image image1;
//    private ImageSprite imageSprite1;
//    private ImageSprite imageSprite2;
//
//    @Before
//    public void setUp() throws Exception {
//
//        MockitoAnnotations.initMocks(this);
//
//        ApiModelRepository modelRepository = new ApiModelRepository();
//
//        image1 = new ImageImpl("abc", modelRepository);
//        image2 = new ImageImpl("def", modelRepository);
//        imageSprite1 = new ImageSpriteImpl(image1, 0, 0, modelRepository);
//        imageSprite2 = new ImageSpriteImpl(image2, 0, 0, modelRepository);
//
//        modelRepository.addImage(image1);
//        modelRepository.addImage(image2);
//        modelRepository.addImageSprite(imageSprite1);
//        modelRepository.addImageSprite(imageSprite2);
//
//        Mockito.when(mockBlurpObjectProvider.getModelRepository()).thenReturn(modelRepository);
//        testCandidate = new RuntimeRepository(mockBlurpObjectProvider);
//        Mockito.when(mockBlurpObjectProvider.getRuntimeRepository()).thenReturn(testCandidate);
//
//    }
//
//    @Test
//    public void syncCallsSyncAllForEachTypeOfObject() throws Exception {
//
//        testCandidate.syncWithModelRepository(1);
//        assertThat(testCandidate.getImage(image1), notNullValue());
//        assertThat(testCandidate.getImage(image2), notNullValue());
//        assertThat(testCandidate.getImageSprite(imageSprite1), notNullValue());
//        assertThat(testCandidate.getImageSprite(imageSprite2), notNullValue());
//    }
//}
