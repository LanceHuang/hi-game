package com.lance.game.demo.module.emulator;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 战斗单元
 *
 * @author Lance
 */
@Getter
@Setter
public class BattleUnit {

    /** 战斗id */
    private long id;

    /** 随机种子 */
    private long seed;

    /** 攻方 */
    private CreatureUnit attacker;

    /** 守方 */
    private CreatureUnit defender;

    /** 时间线 */
    private long timeline;

    /** 指令队列 */
    private Queue<Instruction> instructions;

    /** 战报 */
    private BattleReport battleReport;

    // todo 如何实现持续回血buff？

    public BattleUnit(long id, long seed) {
        this.id = id;
        this.seed = seed;
        this.instructions = new LinkedList<>(); // todo 改为优先队列
        // todo 初始化战报
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
        System.out.println(String.format("================ 【%d】执行【%s】指令 ================",
                instruction.getBaseUnit().getId(), instruction.getType()));
        instruction.execute(this);
    }

    /**
     * 结算
     */
    public void settle() {
        // todo
    }
}
