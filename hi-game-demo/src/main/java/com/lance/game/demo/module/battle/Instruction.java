package com.lance.game.demo.module.battle;

import lombok.Getter;
import lombok.Setter;

/**
 * 指令
 *
 * @author Lance
 */
@Getter
@Setter
public class Instruction {

    private InstructionType type;

    private CreatureUnit baseUnit;

    private CreatureUnit targetUnit;

    /** 执行时间 */
    private long executeTime;

    public Instruction(InstructionType type, CreatureUnit baseUnit) {
        this.type = type;
        this.baseUnit = baseUnit;
    }

    public Instruction(InstructionType type, CreatureUnit baseUnit, CreatureUnit targetUnit) {
        this.type = type;
        this.baseUnit = baseUnit;
        this.targetUnit = targetUnit;
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
