package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.core.commands.*;
import org.junit.*;
import org.mockito.*;

public class CommandExecutorTest {

    @Mock BlurpObjectProvider mockBlurpObjectProvider;
    @Mock private CommandVisitable mockCommand1;
    @Mock private CommandVisitable mockCommand2;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void executesAllCommands() throws Exception {

        CommandExecutor commandExecutor = new CommandExecutor(mockBlurpObjectProvider);
        List<CommandVisitable> commands = Arrays.asList(mockCommand1, mockCommand2);

        commandExecutor.executeAll(commands, 0.1f);
        Mockito.verify(mockCommand1).accept(commandExecutor, 0.1f);
        Mockito.verify(mockCommand2).accept(commandExecutor, 0.1f);
    }
}
