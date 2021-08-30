package com.lance.game.demo.module.battle.handler;

import com.lance.game.demo.module.battle.BattleUnit;
import com.lance.game.demo.module.battle.CreatureUnit;

/**
 * 状态处理器
 *
 * @author Lance
 */
public interface IStateHandler {

    /**
     * 处理状态
     *
     * @param battleUnit   战斗单元
     * @param creatureUnit 生物单元
     */
    void handle(BattleUnit battleUnit, CreatureUnit creatureUnit);
}
