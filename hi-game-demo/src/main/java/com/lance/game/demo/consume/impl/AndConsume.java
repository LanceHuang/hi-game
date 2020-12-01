package com.lance.game.demo.consume.impl;

import com.lance.game.demo.consume.AbstractConsume;
import com.lance.game.demo.util.VerifyResult;
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
    private final List<AbstractConsume> consumes = new LinkedList<>();

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

    @Override
    public void parse(String value) {
        throw new UnsupportedOperationException();
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
