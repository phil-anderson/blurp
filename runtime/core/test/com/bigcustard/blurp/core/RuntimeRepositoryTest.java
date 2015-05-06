package com.bigcustard.blurp.core;

import com.bigcustard.blurp.core.commands.*;
import com.bigcustard.blurp.testutils.*;
import org.junit.*;
import org.mockito.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class RuntimeRepositoryTest extends LibGdxTest {

    RuntimeRepository testCandidate;

    @Mock private CommandVisitable mockCommand;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        testCandidate = new RuntimeRepository();
    }

    @Test
    public void canRegisterCommand() throws Exception {

        assertThat(testCandidate.getCommandRequests().size(), is(0));
        testCandidate.registerCommand(mockCommand);
        assertThat(testCandidate.getCommandRequests().size(), is(1));
    }

    @Test
    public void syncExecutesCommands() throws Exception {

        testCandidate.registerCommand(mockCommand);
        testCandidate.syncWithModelRepository(1);
        Mockito.verify(mockCommand).accept(testCandidate.getCommandExecutor(), 1);
    }
}
