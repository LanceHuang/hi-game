package com.lance.game.demo.module.emulator;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 战斗单元
 *
 * @author Lance
 */
@Data
public class BattleUnit {

    /** 战斗id */
    private long id;

    /** 随机种子 */
    private long seed;

    /** 攻方 */
    private CreatureUnit attacker;

    /** 守方 */
    private CreatureUnit defender;

    /** 指令队列 */
    private Queue<Instruction> instructions;

    /** 战报 */
    private BattleReport battleReport;

    public BattleUnit(long id, long seed) {
        this.id = id;
        this.seed = seed;
        this.instructions = new LinkedList<>();
        // todo 初始化战报
    }

    /**
     * 初始化
     */
    public void init() {
        addInstruction(new Instruction(InstructionType.READY, this.attacker));
        addInstruction(new Instruction(InstructionType.READY, this.defender));

    }

    /**
     * 添加指令
     */
    public void addInstruction(Instruction instruction) {
        if (instruction == null) {
            return;
        }
        this.instructions.add(instruction);
    }

    /**
     * 判断是否有可执行的指令
     */
    public boolean hasNext() {
        return !this.instructions.isEmpty() && !this.attacker.isDead() && !this.defender.isDead();
    }

    /**
     * 获取下一指令
     */
    public Instruction next() {
        return this.instructions.poll();
    }

    /**
     * 执行下一指令
     */
    public void executeNext() {
        Instruction instruction = next();
        System.out.println(String.format("【%d】执行【%s】指令", instruction.getBaseUnit().getId(), instruction.getType()));
        instruction.execute(this);
    }

}
