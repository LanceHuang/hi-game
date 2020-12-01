package com.lance.game.demo.core.condition;

import com.lance.game.demo.core.model.VerifyResult;
import com.lance.game.demo.core.exception.GameException;
import com.lance.game.demo.module.player.model.Player;

/**
 * @author Lance
 */
public abstract class AbstractCondition implements ICondition {

    /**
     * 解析条件值
     */
    public abstract void parse(String value);

    @Override
    public boolean verify(Player player) {
        VerifyResult verifyResult = new VerifyResult();
        doVerify(player, verifyResult);
        return verifyResult.isSuccess();
    }

    @Override
    public void verifyThrow(Player player) throws GameException {
        VerifyResult verifyResult = new VerifyResult();
        doVerify(player, verifyResult);
        if (!verifyResult.isSuccess()) {
            throw new GameException(verifyResult.getErrorCode()); // 支持不同的i18提示
        }
    }

    public abstract void doVerify(Player player, VerifyResult verifyResult);
}
