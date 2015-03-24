package com.bigcustard.blurp.model;

import org.junit.*;

import static junit.framework.TestCase.fail;

public class RepositoryTest {

    @Test // Can't use "expected" as may erroneously throw on first call.
    public void canOnlyInitialiseOnce() throws Exception {

        Repository.getInstance().initialise(null);
        try {
            Repository.getInstance().initialise(null);
            fail("Should've thrown IllegalStateException on second call to initialise");
        } catch(IllegalStateException e) { }
    }
}
