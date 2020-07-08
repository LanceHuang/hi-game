package com.lance.game.demo.module.emulator.handler;

import com.lance.game.demo.module.emulator.BattleUnit;
import com.lance.game.demo.module.emulator.CreatureUnit;
import com.lance.game.demo.module.emulator.Instruction;
import com.lance.game.demo.module.emulator.InstructionType;

/**
 * 正常状态处理器
 *
 * @author Lance
 */
public class NormalStateHandler implements IStateHandler {

    @Override
    public void handle(BattleUnit battleUnit, CreatureUnit creatureUnit) {
        battleUnit.addInstruction(new Instruction(InstructionType.HIT, creatureUnit));
    }
}
