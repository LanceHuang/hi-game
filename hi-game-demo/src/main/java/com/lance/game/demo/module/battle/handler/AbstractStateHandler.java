package com.lance.game.demo.module.battle.handler;

import com.lance.game.demo.module.battle.BattleUnit;
import com.lance.game.demo.module.battle.CreatureUnit;

/**
 * @author Lance
 */
public abstract class AbstractStateHandler implements IStateHandler {

    /**
     * 处理状态
     *
     * @param battleUnit   战斗单元
     * @param creatureUnit 生物单元
     */
    public static void handleState(BattleUnit battleUnit, CreatureUnit creatureUnit) {
        creatureUnit.getState().getStateHandler().handle(battleUnit, creatureUnit);
    }
}
