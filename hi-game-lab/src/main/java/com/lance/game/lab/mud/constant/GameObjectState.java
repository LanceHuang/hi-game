package com.lance.game.lab.mud.constant;

import com.lance.game.lab.mud.statehandler.impl.BackToTheCityStateHandler;
import com.lance.game.lab.mud.statehandler.impl.GatherStateHandler;
import com.lance.game.lab.mud.statehandler.impl.NoneStateHandler;
import com.lance.game.lab.mud.statehandler.impl.PatrolStateHandler;
import com.lance.game.lab.mud.statehandler.StateHandler;

/**
 * 游戏单位状态
 *
 * @author Lance
 * @since 2021/9/6
 */
public enum GameObjectState {

    /** 发呆 */
    NONE(new NoneStateHandler()),
    /** 巡逻 */
    PATROL(new PatrolStateHandler()),
    /** 采集 */
    GATHER(new GatherStateHandler()),
    /** 回城 */
    BACK_TO_THE_CITY((new BackToTheCityStateHandler())),
    ;

    private final StateHandler stateHandler;

    GameObjectState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    public StateHandler getArmyStateHandler() {
        return stateHandler;
    }
}
