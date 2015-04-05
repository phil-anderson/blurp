package com.bigcustard.blurp.model;

import com.bigcustard.blurp.core.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpriteTest {

    @Mock
    ModelRepository mockModelRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        MSS.dispose();
        MSS.setInstances(mockModelRepository, null);
    }

    @Test
    public void rotateIsAdditive() throws Exception {

        ImageSprite testCandidate = new ImageSprite("ABC", 100, 100);
        testCandidate.rotation = 45;
        testCandidate.rotateBy(45);
        assertThat(testCandidate.rotation, is(90.0));
    }

    @Test
    public void scaleAffectsBothXAndY() throws Exception {

        ImageSprite testCandidate = new ImageSprite("ABC", 100, 100);
        testCandidate.scale(10);
        assertThat(testCandidate.scaleX, is(10.0));
        assertThat(testCandidate.scaleY, is(10.0));
    }
}
