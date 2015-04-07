package com.bigcustard.blurp.model.commands;

import com.bigcustard.blurp.model.*;
import com.bigcustard.blurp.model.testutils.*;
import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MoveTowardsExecutorTest {

    private MoveTowardsExecutor testCandidate;
    private Sprite testSprite;

    @Before
    public void setUp() throws Exception {

        testCandidate = new MoveTowardsExecutor();
        testSprite = new SpriteForTests();
        testSprite.setPosition(0, 0);
    }

    @Test
    public void interpolatesBasicPosition() {

        // Should move whole distance in exactly a second.
        MoveTowardsCommand request = new MoveTowardsCommand(testSprite, 4, 3, 5);

        testCandidate.execute(request, 0.25f);
        assertThat(testSprite.x, is(1.0));
        assertThat(testSprite.y, is(0.75));

        testCandidate.execute(request, 0.25f);
        assertThat(testSprite.x, is(2.0));
        assertThat(testSprite.y, is(1.5));

        testCandidate.execute(request, 0.5f);
        assertThat(testSprite.x, is(4.0));
        assertThat(testSprite.y, is(3.0));
    }

    @Test
    public void handlesNegativeDirection() {

        MoveTowardsCommand request = new MoveTowardsCommand(testSprite, -4, -3, 5);

        testCandidate.execute(request, 0.25f);
        assertThat(testSprite.x, is(-1.0));
        assertThat(testSprite.y, is(-0.75));

        testCandidate.execute(request, 0.25f);
        assertThat(testSprite.x, is(-2.0));
        assertThat(testSprite.y, is(-1.5));

        testCandidate.execute(request, 0.5f);
        assertThat(testSprite.x, is(-4.0));
        assertThat(testSprite.y, is(-3.0));
    }

    @Test
    public void handlesOvershoot() {

        MoveTowardsCommand request = new MoveTowardsCommand(testSprite, 4, 3, 5);
        testCandidate.execute(request, 99f);
        assertThat(testSprite.x, is(4.0));
        assertThat(testSprite.y, is(3.0));

        request = new MoveTowardsCommand(testSprite, -1, -1, 5);
        testCandidate.execute(request, 99f);
        assertThat(testSprite.x, is(-1.0));
        assertThat(testSprite.y, is(-1.0));
    }
}
