package com.bigcustard.blurp.core;

import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.testutils.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class RuntimeRepositoryTest extends LibGdxTest {

    RuntimeRepository testCandidate;

    @Mock private Command mockCommand;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        testCandidate = new RuntimeRepository();
    }

    @Test
    public void canRegisterCommand() throws Exception {

        assertThat(testCandidate.getCommands().size(), is(0));
        testCandidate.registerCommand(mockCommand);
        assertThat(testCandidate.getCommands().size(), is(1));
    }

}
