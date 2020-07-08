package com.lance.game.demo.module.emulator;

import com.lance.game.demo.module.emulator.handler.IStateHandler;
import com.lance.game.demo.module.emulator.handler.NormalStateHandler;

/**
 * 状态
 *
 * @author Lance
 */
public enum State {

    /** 正常 */
    NORMAL(new NormalStateHandler()),
    ;
    /** 眩晕 */
    /** 嘲讽 */

    private IStateHandler stateHandler;

    State(IStateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    public IStateHandler getStateHandler() {
        return stateHandler;
    }
}
