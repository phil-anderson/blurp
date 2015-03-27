package com.bigcustard.blurp.model;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SpriteTest {

    @Test
    public void rotateIsAdditive() throws Exception {

        ImageSprite testCandidate = new ImageSprite("ABC", 100, 100);
        testCandidate.rotation = 45;
        testCandidate.rotate(45);
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
