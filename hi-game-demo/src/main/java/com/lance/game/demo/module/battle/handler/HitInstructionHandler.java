package com.lance.game.demo.module.battle.handler;

import com.lance.game.demo.module.battle.BattleUnit;
import com.lance.game.demo.module.battle.CreatureUnit;
import com.lance.game.demo.module.battle.Instruction;

/**
 * 攻击指令
 *
 * @author Lance
 */
public class HitInstructionHandler extends AbstractInstructionHandler {

    @Override
    public void doExecute(BattleUnit battleUnit, Instruction instruction) {
        CreatureUnit attacker = instruction.getBaseUnit();
        CreatureUnit defender = instruction.getTargetUnit();
        defender.reduceHp(attacker.getAtk()); // todo 这里可能还要触发守方的被动，或者换种方式实现
        System.out.println(
                String.format("【%s】普攻【%s】，【%s】扣除【%d】血，剩余【%d】血",
                        attacker.getId(), defender.getId(), defender.getId(), attacker.getAtk(), defender.getHp()));
    }
}
