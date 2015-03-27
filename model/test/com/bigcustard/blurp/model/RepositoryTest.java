package com.bigcustard.blurp.model;

import org.junit.*;

import static junit.framework.TestCase.fail;

public class RepositoryTest {

    @Test // Can't use "expected" as may erroneously throw on first call.
    public void canOnlyInitialiseOnce() throws Exception {

        Repository.getInstance().resetSingleton();
        Repository.getInstance().initialise(null, 0, 0);
        try {
            Repository.getInstance().initialise(null, 0, 0);
            fail("Should've thrown IllegalStateException on second call to initialise");
        } catch(IllegalStateException e) { }
    }
}
