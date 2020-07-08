package com.lance.game.demo.module.emulator.handler;

import com.lance.game.demo.module.emulator.BattleUnit;
import com.lance.game.demo.module.emulator.CreatureUnit;
import com.lance.game.demo.module.emulator.Instruction;

/**
 * 攻击指令
 *
 * @author Lance
 */
public class HitInstructionHandler extends AbstractInstructionHandler {

    @Override
    public void doExecute(BattleUnit battleUnit, Instruction instruction) {
        CreatureUnit attacker = instruction.getBaseUnit();
        CreatureUnit defender = (battleUnit.getAttacker() == attacker) ? battleUnit.getDefender() : battleUnit.getAttacker();
        defender.reduceHp(attacker.getAtk());
        System.out.println(
                String.format("【%s】普攻【%s】，【%s】扣除【%d】血，剩余【%d】血",
                        attacker.getId(), defender.getId(), defender.getId(), attacker.getAtk(), defender.getHp()));
    }
}
