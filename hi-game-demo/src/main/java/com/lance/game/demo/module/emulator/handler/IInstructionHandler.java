package com.lance.game.demo.module.emulator.handler;

import com.lance.game.demo.module.emulator.BattleUnit;
import com.lance.game.demo.module.emulator.Instruction;

/**
 * 指令处理器
 *
 * @author Lance
 */
public interface IInstructionHandler {

    /**
     * 执行指令
     *
     * @param battleUnit  战斗单元
     * @param instruction 指令
     */
    void execute(BattleUnit battleUnit, Instruction instruction);

}
