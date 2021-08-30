package com.lance.game.demo.module.battle;

import org.junit.Test;

public class BattleEmulatorTest {

    @Test
    public void test() {
        long id = 1;
        long seed = System.currentTimeMillis();

        // 添加战斗单元
        BattleUnit battleUnit = new BattleUnit(id, seed);
        CreatureUnit attacker = new CreatureUnit(10086L);
        attacker.setAtk(100L);
        attacker.setMaxHp(1000L);
        attacker.setHp(1000L);
        battleUnit.setAttacker(attacker);
        CreatureUnit defender = new CreatureUnit(10087L);
        defender.setAtk(80L);
        defender.setMaxHp(2000L);
        defender.setHp(2000L);
        battleUnit.setDefender(defender);

        // 添加初始指令
        battleUnit.addInstruction(new Instruction(InstructionType.READY, attacker));
        battleUnit.addInstruction(new Instruction(InstructionType.READY, defender));
        System.out.println(BattleEmulator.fight(battleUnit));
    }
}