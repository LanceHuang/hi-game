package com.lance.game.lab.statemachine;

import com.lance.game.lab.statemachine.handler.BackToTheCityStateHandler;
import com.lance.game.lab.statemachine.handler.GatherStateHandler;
import com.lance.game.lab.statemachine.handler.NoneStateHandler;
import com.lance.game.lab.statemachine.handler.PatrolStateHandler;
import com.lance.game.lab.statemachine.handler.StateHandler;

/**
 * 军队状态
 *
 * @author Lance
 * @since 2021/9/6
 */
public enum ArmyState {

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

    ArmyState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    public StateHandler getArmyStateHandler() {
        return stateHandler;
    }
}
