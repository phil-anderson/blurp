package com.bigcustard.blurp.core;

import com.bigcustard.blurp.utils.*;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BlurpifierTest {

    private Blurpifier testCandidate;
    private BlurpifierTest.MockRenderer mockRenderer;

    @Before
    public void setUp() throws Exception {

        testCandidate = new Blurpifier();
    }

    @Test
    public void blurpifyBlocksUntilStateIsComplete() throws Exception {

        mockRenderer = new MockRenderer(false);
        assertBlocksUntilComplete();
    }

    @Test @Ignore
    public void blurpifyBlocksUntilStateIsCompleteWithSlowRenderer() throws Exception {

        mockRenderer = new MockRenderer(true);
        assertBlocksUntilComplete();
    }

    private void assertBlocksUntilComplete() {

        new Thread(mockRenderer).start();

        // Ensure mock renderer thread has started and has asserted the dormant state before calling blurpify.
        while(!mockRenderer.dormantAsserted) Sleep.forOneMs();

        testCandidate.blurpify();
        assertThat(testCandidate.getState(), is(Blurpifier.State.Dormant)); // Should be back to dormant again
        assertThat(mockRenderer.complete, is(true)); // Just in case dormant state got set elsewhere and mock renderer thread is still running
    }

    private class MockRenderer implements Runnable {

        private volatile boolean dormantAsserted;
        private volatile boolean complete;

        private boolean delayBeforeCompleting;

        private MockRenderer(boolean delayBeforeCompleting) {

            this.delayBeforeCompleting = delayBeforeCompleting;
        }

        @Override
        public void run() {

            assertThat(testCandidate.getState(), is(Blurpifier.State.Dormant));
            dormantAsserted = true;

            // Wait for blurpify request from main thread
            while(testCandidate.getState() != Blurpifier.State.Requested) Sleep.forOneMs();

            // Give the thread plenty of time to block
            if(delayBeforeCompleting) Sleep.forDuration(10000);
            testCandidate.setState(Blurpifier.State.Complete);
            complete = true;
        }
    }
}
