package com.lance.game.lab.mud;

import com.lance.game.lab.mud.cmd.GameCommandType;

import java.util.Map;

/**
 * @author Lance
 * @since 2021/9/7
 */
public interface IMudService {

    /**
     * 创建战役
     *
     * @return 战役id
     */
    long createBattleContext();

    /**
     * 创建游戏单位，并放置到 (x,y)
     *
     * @param battleId 战役id
     * @param configId 配置id
     * @param x        x
     * @param y        y
     * @return 对象id
     */
    long makeGameObject(long battleId, int configId, int x, int y);

    /**
     * 操作游戏单位
     *
     * @param battleId        战役id
     * @param id              对象id
     * @param gameCommandType 游戏指令
     * @param params          拓展参数
     */
    void executeGameObject(long battleId, long id, GameCommandType gameCommandType, Map<String, String> params);
}
