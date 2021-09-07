package com.lance.game.lab.mud.battle.action;

import com.lance.game.lab.mud.battle.BattleContext;
import com.lance.game.lab.mud.gameobject.GameObject;

import java.util.Map;

/**
 * 行为
 *
 * @author Lance
 * @since 2021/9/7
 */
public abstract class GameAction {

    /**
     * 游戏执行行为
     *
     * @param battleContext 战役
     * @param gameObject    游戏对象
     * @param params        参数
     */
    public abstract void execute(BattleContext battleContext, GameObject gameObject, Map<String, String> params);
}
