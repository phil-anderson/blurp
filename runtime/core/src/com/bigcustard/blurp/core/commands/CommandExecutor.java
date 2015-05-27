package com.bigcustard.blurp.core.commands;

import java.util.*;
import com.bigcustard.blurp.core.*;

public class CommandExecutor implements CommandVisitor {

    private final SetDebugModeExecutor setDebugModeExecutor;
    private final RunEffectExecutor runEffectExecutor;
    private final ZOrderExecutor zOrderExecutor;
    private final List<CommandVisitable> deferredCommands;

    private boolean runningDeferred;

    public CommandExecutor() {


        setDebugModeExecutor = new SetDebugModeExecutor();
        runEffectExecutor = new RunEffectExecutor();
        zOrderExecutor = new ZOrderExecutor(this);
        deferredCommands = new ArrayList<CommandVisitable>();
    }

    public void executeCommands(List<CommandVisitable> commandRequests, float deltaTime) {

        for(CommandVisitable request : commandRequests) {
            request.accept(this, deltaTime);
        }
    }

    public void executeDeferredCommands(float deltaTime) {

        runningDeferred = true;
        try {
            executeCommands(deferredCommands, deltaTime);
            deferredCommands.clear();
        } finally {
            runningDeferred = false;
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

        BlurpStore.runtimeConsole.print(consolePrintCommand.getTextToPrint(), consolePrintCommand.getColour(), consolePrintCommand.getAlpha());
    }

    @Override
    public void visit(ConsoleClearCommand consoleClearCommand) {

        BlurpStore.runtimeConsole.clear();
    }

    @Override
    public void visit(SetZOrderCommand setZOrderCommand) {

        zOrderExecutor.execute(setZOrderCommand);
    }

    @Override
    public void visit(ChangeZOrderCommand changeZOrderCommand) {

        zOrderExecutor.execute(changeZOrderCommand);
    }

    @Override
    public void visit(HandleLayerCommand handleLayerCommand) {

        BlurpStore.blurpScreen.handleSpriteLayer(handleLayerCommand.getSprite());
    }

    // Defer commands that need to process AFTER the syncing process
    public void deferCommand(CommandVisitable command) {

        if(!runningDeferred) deferredCommands.add(command);
    }
}
