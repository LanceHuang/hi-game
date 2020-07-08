package com.lance.game.demo.module.emulator;

import lombok.Data;

/**
 * 指令
 *
 * @author Lance
 */
@Data
public class Instruction {

    private InstructionType type;

    private CreatureUnit baseUnit;

    public Instruction(InstructionType type, CreatureUnit baseUnit) {
        this.type = type;
        this.baseUnit = baseUnit;
    }

    /**
     * 执行行为指令
     *
     * @param battleUnit 战斗单元
     */
    public void execute(BattleUnit battleUnit) {
        this.type.getInstructionHandler().execute(battleUnit, this);
    }
}
