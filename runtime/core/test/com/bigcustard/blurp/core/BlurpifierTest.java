package com.bigcustard.blurp.core;

import org.junit.*;
import org.mockito.*;

import static com.bigcustard.blurp.core.Blurpifier.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class BlurpifierTest {

    @Mock
    private Blurpifier testCandidate;
    private BlurpifierTest.MockRenderer mockRenderer;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
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
        while(!mockRenderer.dormantAsserted) BlurpStore.utils.rest(0.001);

        testCandidate.blurpify();
        assertThat(testCandidate.getRequestState(), is(BlurpifyRequestState.Dormant)); // Should be back to dormant again
        assertThat(mockRenderer.complete, is(true)); // Just in case dormant state got set elsewhere and mock renderer thread is still running
    }

    private class MockRenderer implements Runnable {

        private volatile boolean dormantAsserted;
        private volatile boolean complete;

        private boolean delayBeforeStateChange;

        private MockRenderer(boolean delayBeforeStateChange) {

            this.delayBeforeStateChange = delayBeforeStateChange;
        }

        @Override
        public void run() {

            assertThat(testCandidate.getRequestState(), is(BlurpifyRequestState.Dormant));
            dormantAsserted = true;

            // Wait for blurpify request from main thread
            while(testCandidate.getRequestState() != BlurpifyRequestState.Requested) BlurpStore.utils.rest(0.001);

            if(delayBeforeStateChange) BlurpStore.utils.rest(5);
            synchronized(testCandidate) {
                testCandidate.setRenderState(BlurpifyRenderState.RequestAcknowledged);
            }

            if(delayBeforeStateChange) BlurpStore.utils.rest(5);
            synchronized(testCandidate) {
                testCandidate.setRenderState(BlurpifyRenderState.RequestComplete);
            }
            complete = true;
        }
    }
}
