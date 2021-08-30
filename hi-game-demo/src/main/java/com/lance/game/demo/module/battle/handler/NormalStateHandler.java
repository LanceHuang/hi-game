package com.lance.game.demo.module.battle.handler;

import com.lance.game.demo.module.battle.BattleUnit;
import com.lance.game.demo.module.battle.CreatureUnit;
import com.lance.game.demo.module.battle.Instruction;
import com.lance.game.demo.module.battle.InstructionType;

/**
 * 正常状态处理器
 *
 * @author Lance
 */
public class NormalStateHandler implements IStateHandler {

    @Override
    public void handle(BattleUnit battleUnit, CreatureUnit creatureUnit) {

        // 判断是否处于眩晕、嘲讽状态

        // 判断是否能释放技能

        // 默认普攻

        // todo 需要判断阵营，选择攻击目标，这里暂时只处理两个角色
        CreatureUnit attacker = creatureUnit;
        CreatureUnit defender = (battleUnit.getAttacker() == attacker) ? battleUnit.getDefender() : battleUnit.getAttacker();
        battleUnit.addInstruction(new Instruction(InstructionType.HIT, creatureUnit, defender));
    }
}
