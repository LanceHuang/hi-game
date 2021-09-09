package com.lance.game.lab.mud.action;

import com.lance.game.lab.mud.action.impl.GatherAction;
import com.lance.game.lab.mud.action.impl.MakeGameObjectAction;
import com.lance.game.lab.mud.gameobject.BattleContext;
import com.lance.game.lab.mud.gameobject.GameObject;

import java.util.Map;

/**
 * 行为类型
 *
 * @author Lance
 * @since 2021/9/7
 */
public enum GameActionType {

    /** 制造游戏单位 */
    MAKE_GAME_OBJECT(new MakeGameObjectAction()),
    /** 采集 */
    GATHER(new GatherAction()),
//    /** 制造建筑物 */
//    BUILDING(),
    ;

    private final GameAction gameAction;

    GameActionType(GameAction gameAction) {
        this.gameAction = gameAction;
    }

    /**
     * 执行行为
     *
     * @param battleContext 战役
     * @param gameObject    游戏单位
     * @param params        参数
     */
    public void execute(BattleContext battleContext, GameObject gameObject, Map<String, String> params) {
        gameAction.execute(battleContext, gameObject, params);
    }
}
