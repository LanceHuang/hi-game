package com.lance.game.demo.module.battle.handler;

import com.lance.game.demo.module.battle.BattleUnit;
import com.lance.game.demo.module.battle.Instruction;

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
