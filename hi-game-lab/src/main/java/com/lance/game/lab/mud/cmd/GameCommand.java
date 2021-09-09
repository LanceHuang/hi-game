package com.lance.game.lab.mud.cmd;

import com.lance.game.lab.mud.gameobject.BattleContext;
import com.lance.game.lab.mud.gameobject.GameObject;

/**
 * 游戏指令
 *
 * @author Lance
 * @since 2021/9/9
 */
public interface GameCommand {

    /**
     * 执行指令
     *
     * @param battleContext 战役
     * @param gameObject    操作单位
     * @return 执行结果
     */
    boolean executeCmd(BattleContext battleContext, GameObject gameObject);

    // todo params
}
