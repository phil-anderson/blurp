package com.bigcustard.blurp.core.common;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.testutils.*;
import org.junit.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class MoveTowardsHandlerTest {

    private Sprite testSprite;

    @Before
    public void setUp() throws Exception {

        testSprite = new SpriteForTests();
        testSprite.setPosition(0, 0);
    }

    @Test
    public void interpolatesBasicPosition() {

        MoveTowardsHandler.moveTowards(testSprite, 4, 3, 2.5);
        assertThat(testSprite.x, is(2.0));
        assertThat(testSprite.y, is(1.5));

        MoveTowardsHandler.moveTowards(testSprite, 4, 3, 2.5);
        assertThat(testSprite.x, is(4.0));
        assertThat(testSprite.y, is(3.0));

    }

    @Test
    public void handlesNegativeDirection() {

        MoveTowardsHandler.moveTowards(testSprite, -4, -3, 2.5);
        assertThat(testSprite.x, is(-2.0));
        assertThat(testSprite.y, is(-1.5));

        MoveTowardsHandler.moveTowards(testSprite, -4, -3, 2.5);
        assertThat(testSprite.x, is(-4.0));
        assertThat(testSprite.y, is(-3.0));
    }

    @Test
    public void handlesOvershoot() {

        MoveTowardsHandler.moveTowards(testSprite, 4, 3, 99);
        assertThat(testSprite.x, is(4.0));
        assertThat(testSprite.y, is(3.0));

        MoveTowardsHandler.moveTowards(testSprite, -4, -3, 99);
        assertThat(testSprite.x, is(-4.0));
        assertThat(testSprite.y, is(-3.0));
    }

    @Test
    public void handlesNegativeDistance() {

        MoveTowardsHandler.moveTowards(testSprite, 4, 3, -5);
        assertThat(testSprite.x, is(-4.0));
        assertThat(testSprite.y, is(-3.0));

        MoveTowardsHandler.moveTowards(testSprite, 4, 3, -5);
        assertThat(testSprite.x, is(-8.0));
        assertThat(testSprite.y, is(-6.0));

    }
}
