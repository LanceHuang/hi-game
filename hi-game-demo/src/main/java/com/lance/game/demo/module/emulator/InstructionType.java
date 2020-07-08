package com.lance.game.demo.module.emulator;

import com.lance.game.demo.module.emulator.handler.HitInstructionHandler;
import com.lance.game.demo.module.emulator.handler.IInstructionHandler;
import com.lance.game.demo.module.emulator.handler.ReadyInstructionHandler;

/**
 * 指令类型
 *
 * @author Lance
 */
public enum InstructionType {

    /** 准备 */
    READY(new ReadyInstructionHandler()),
    /** 普攻 */
    HIT(new HitInstructionHandler()),
    ;

    private IInstructionHandler instructionHandler;

    InstructionType(IInstructionHandler instructionHandler) {
        this.instructionHandler = instructionHandler;
    }

    public IInstructionHandler getInstructionHandler() {
        return instructionHandler;
    }
}
