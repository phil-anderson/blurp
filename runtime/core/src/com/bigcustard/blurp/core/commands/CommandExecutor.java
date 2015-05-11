package com.bigcustard.blurp.core.commands;

import java.util.*;
import com.bigcustard.blurp.core.*;

public class CommandExecutor implements CommandVisitor {

    private final SetDebugModeExecutor setDebugModeExecutor;
    private final RunEffectExecutor runEffectExecutor;

    public CommandExecutor() {

        setDebugModeExecutor = new SetDebugModeExecutor();
        runEffectExecutor = new RunEffectExecutor();
    }

    public void executeAll(List<CommandVisitable> commandRequests, float deltaTime) {

        for(CommandVisitable request : commandRequests) {
            request.accept(this, deltaTime);
        }
    }

    @Override
    public void visit(SetDebugModeCommand setDebugModeCommand) {

        setDebugModeExecutor.execute(setDebugModeCommand);
    }

    @Override
    public void visit(RunEffectCommand runEffectCommand) {

        runEffectExecutor.execute(runEffectCommand);
    }

    @Override
    public void visit(ConsolePrintCommand consolePrintCommand) {

        BlurpStore.runtimeConsole.print(consolePrintCommand.getTextToPrint(), consolePrintCommand.getColour());
    }

    @Override
    public void visit(ConsoleClearCommand consoleClearCommand) {

        BlurpStore.runtimeConsole.clear();
    }
}
