package com.lance.game.demo.condition.impl;

import com.lance.game.demo.condition.AbstractCondition;
import com.lance.game.demo.util.VerifyResult;
import com.lance.game.demo.module.player.model.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * 与条件
 *
 * @author Lance
 */
public class AndCondition extends AbstractCondition {

    /** 条件集 */
    private final List<AbstractCondition> conditions = new LinkedList<>();

    @Override
    public void doVerify(Player player, VerifyResult verifyResult) {
        for (AbstractCondition condition : this.conditions) {
            condition.doVerify(player, verifyResult);
            if (!verifyResult.isSuccess()) {
                return;
            }
        }
    }

    @Override
    public void parse(String value) {
        throw new UnsupportedOperationException();
    }

    /**
     * 添加子条件
     */
    public void addCondition(AbstractCondition condition) {
        if (condition == null) {
            return;
        }
        this.conditions.add(condition);
    }
}
