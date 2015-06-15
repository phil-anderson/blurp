package com.bigcustard.blurp.model;

import com.bigcustard.blurp.model.testutils.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SpriteTest {

    @Test
    public void rotateIsAdditive() throws Exception {

        Sprite testCandidate = new SpriteForTests();
        testCandidate.angle = 45;
        testCandidate.rotateBy(45);
        assertThat(testCandidate.angle, is(90.0));
    }

    @Test
    public void scaleAffectsBothXAndY() throws Exception {

        Sprite testCandidate = new SpriteForTests();
        testCandidate.scale(10);
        assertThat(testCandidate.scaleX, is(10.0));
        assertThat(testCandidate.scaleY, is(10.0));
    }

    @Test
    public void flipsReverseScale() throws Exception {

        Sprite testCandidate = new SpriteForTests();
        testCandidate.position(10, 10);
        testCandidate.scale(10);
        testCandidate.flipX();
        assertThat(testCandidate.scaleX, is(-10.0));
        assertThat(testCandidate.scaleY, is(10.0));
        testCandidate.flipY();
        assertThat(testCandidate.scaleX, is(-10.0));
        assertThat(testCandidate.scaleY, is(-10.0));
    }
}
