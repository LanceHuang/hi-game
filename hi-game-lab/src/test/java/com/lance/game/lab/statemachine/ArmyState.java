package com.lance.game.lab.statemachine;

import com.lance.game.lab.statemachine.handler.ArmyNoneStateHandler;
import com.lance.game.lab.statemachine.handler.ArmyPatrolStateHandler;
import com.lance.game.lab.statemachine.handler.ArmyStateHandler;

/**
 * 军队状态
 *
 * @author Lance
 * @since 2021/9/6
 */
public enum ArmyState {

    /** 发呆 */
    NONE(new ArmyNoneStateHandler()),
    /** 巡逻 */
    PATROL(new ArmyPatrolStateHandler());

    private ArmyStateHandler armyStateHandler;

    ArmyState(ArmyStateHandler armyStateHandler) {
        this.armyStateHandler = armyStateHandler;
    }

    public ArmyStateHandler getArmyStateHandler() {
        return armyStateHandler;
    }
}
