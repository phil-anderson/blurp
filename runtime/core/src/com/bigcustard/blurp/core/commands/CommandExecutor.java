package com.bigcustard.blurp.core.commands;

import java.util.*;
import com.bigcustard.blurp.core.*;

public class CommandExecutor implements CommandVisitor {

    private final BlurpObjectProvider blurpObjectProvider;

    private final SetDebugModeExecutor setDebugModeExecutor;

    public CommandExecutor(BlurpObjectProvider blurpObjectProvider) {

        this.blurpObjectProvider = blurpObjectProvider;

        setDebugModeExecutor = new SetDebugModeExecutor();
    }

    public void executeAll(List<CommandVisitable> commandRequests, float deltaTime) {

        for(CommandVisitable request : commandRequests) {
            request.accept(this, deltaTime);
        }
    }

    @Override
    public void visit(SetDebugModeCommand setDebugModeCommand) {

        setDebugModeExecutor.execute(setDebugModeCommand, blurpObjectProvider.getBlurpScreen());
    }
}
