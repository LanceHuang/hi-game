package com.lance.game.demo.core.consume;

import com.lance.game.demo.core.model.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * 与消耗
 *
 * @author Lance
 */
public class AndConsume extends AbstractConsume {

    /** 消耗集 */
    private List<AbstractConsume> consumes = new LinkedList<>();

    @Override
    public void parse(String value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void doVerify(Player player, VerifyResult verifyResult) {
        for (AbstractConsume consume : this.consumes) {
            consume.doVerify(player, verifyResult);
            if (!verifyResult.isSuccess()) {
                return;
            }
        }
    }

    @Override
    public void consume(Player player) {
        for (AbstractConsume consume : this.consumes) {
            consume.consume(player);
        }
    }

    /**
     * 添加子消耗
     */
    public void addConsume(AbstractConsume consume) {
        if (consume == null) {
            return;
        }
        this.consumes.add(consume);
    }
}
