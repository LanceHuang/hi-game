package com.lance.game.demo.module.battle.handler;

import com.lance.game.demo.module.battle.BattleUnit;
import com.lance.game.demo.module.battle.Instruction;

/**
 * @author Lance
 */
public abstract class AbstractInstructionHandler implements IInstructionHandler {

    @Override
    public void execute(BattleUnit battleUnit, Instruction instruction) {
        doExecute(battleUnit, instruction);
        postExecute(battleUnit, instruction);
    }

    /**
     * 执行指令
     *
     * @param battleUnit  战斗单元
     * @param instruction 指令
     */
    public abstract void doExecute(BattleUnit battleUnit, Instruction instruction);

    /**
     * 执行后处理
     *
     * @param battleUnit  战斗单元
     * @param instruction 指令
     */
    public void postExecute(BattleUnit battleUnit, Instruction instruction) {
        AbstractStateHandler.handleState(battleUnit, instruction.getBaseUnit());
    }

}
