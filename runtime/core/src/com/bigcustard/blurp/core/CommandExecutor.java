package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.core.commands.*;

public class CommandExecutor implements CommandVisitor {

    private MoveTowardsExecutor moveTowardsExecutor;

    public CommandExecutor() {

        moveTowardsExecutor = new MoveTowardsExecutor();
    }

    public void executeAll(List<CommandVisitable> commandRequests, float deltaTime) {

        for(CommandVisitable request : commandRequests) {
            request.accept(this, deltaTime);
        }
    }

    @Override
    public void visit(MoveTowardsCommand moveTowardsCommand, float deltaTime) {

        moveTowardsExecutor.execute(moveTowardsCommand, deltaTime);
    }
}
