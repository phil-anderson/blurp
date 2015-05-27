package com.bigcustard.blurp.core.commands;

// What a crazy load of visitor-pattern-hoops we have to jump through because of Java's type system.
public interface CommandVisitor {

    void visit(SetDebugModeCommand setDebugModeCommand);

    void visit(RunEffectCommand runEffectCommand);

    void visit(ConsolePrintCommand consolePrintCommand);

    void visit(ConsoleClearCommand consoleClearCommand);

    void visit(SetZOrderCommand setZOrderCommand);

    void visit(ChangeZOrderCommand changeZOrderCommand);

    void visit(HandleLayerCommand handleLayerCommand);
}
