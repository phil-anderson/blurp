package com.bigcustard.blurp.core;

import java.util.*;
import com.bigcustard.blurp.core.commands.*;

public class CommandExecutor implements CommandRequestVisitor {

    private MoveTowardsExecutor moveTowardsExecutor;

    public CommandExecutor() {

        moveTowardsExecutor = new MoveTowardsExecutor();
    }

    public void executeAll(List<CommandRequestVisitable> commandRequests, float deltaTime) {

        for(CommandRequestVisitable request : commandRequests) {
            request.accept(this, deltaTime);
        }
    }

    @Override
    public void visit(MoveTowardsRequest moveTowardsRequest, float deltaTime) {

        moveTowardsExecutor.execute(moveTowardsRequest, deltaTime);
    }
}
