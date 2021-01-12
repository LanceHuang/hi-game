package com.lance.game.demo.core.consume;

import com.lance.game.demo.core.exception.GameException;
import com.lance.game.demo.module.player.model.Player;
import com.lance.game.demo.util.VerifyResult;

/**
 * @author Lance
 */
public abstract class AbstractConsume implements IConsume {

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

    @Override
    public void verifyAndConsume(Player player) throws GameException {
        verifyThrow(player);
        consume(player);
    }

    /**
     * 条件校验
     *
     * @param verifyResult 校验结果
     */
    public abstract void doVerify(Player player, VerifyResult verifyResult);

    /**
     * 解析配置
     *
     * @param value 配置信息
     */
    public abstract void parse(String value);
}
