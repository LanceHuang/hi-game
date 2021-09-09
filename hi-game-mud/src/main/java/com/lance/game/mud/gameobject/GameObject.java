package com.lance.game.mud.gameobject;

import com.lance.game.mud.action.GameAction;
import com.lance.game.mud.constant.GameObjectState;
import com.lance.game.mud.constant.GameObjectStateChange;
import org.springframework.statemachine.StateMachine;

/**
 * 游戏单位
 *
 * @author Lance
 * @since 2021/9/7
 */
public abstract class GameObject {

    /** 单位id */
    private final long id;

    private StateMachine<GameObjectState, GameObjectStateChange> stateMachine;

    public GameObject(long id) {
        this.id = id;
    }

    public void tick(BattleContext battleContext) {
        GameObjectState currState = stateMachine.getState().getId();
        // todo action
        GameAction gameAction = null;
        gameAction.execute(battleContext, this, null);
    }

    public long getId() {
        return id;
    }

    public StateMachine<GameObjectState, GameObjectStateChange> getStateMachine() {
        return stateMachine;
    }

    public void setStateMachine(StateMachine<GameObjectState, GameObjectStateChange> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameObject that = (GameObject) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
