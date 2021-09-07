package com.lance.game.lab.mud;

import com.lance.game.lab.mud.battle.BattleContext;

/**
 * @author Lance
 * @since 2021/9/7
 */
public class Mud {

    /**
     * 创建一场战役
     *
     * @param id 战役id
     * @return 战役
     */
    public static BattleContext createBattleContext(long id) {
        return new BattleContext(id);
    }
}
