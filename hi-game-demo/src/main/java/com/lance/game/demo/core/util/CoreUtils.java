package com.lance.game.demo.core.util;

import com.lance.game.demo.core.condition.AbstractCondition;
import com.lance.game.demo.core.condition.AndCondition;
import com.lance.game.demo.core.constant.ConditionType;
import com.lance.game.demo.core.constant.ConsumeType;
import com.lance.game.demo.core.consume.AbstractConsume;
import com.lance.game.demo.core.consume.AndConsume;
import com.lance.game.demo.core.consume.IConsume;
import com.lance.game.demo.core.model.ConditionDef;
import com.lance.game.demo.core.condition.ICondition;
import com.lance.game.demo.core.model.ConsumeDef;

/**
 * @author Lance
 */
public class CoreUtils {

    private static final ICondition trueCondition = ConditionType.TRUE.create();
    private static final IConsume   trueConsume   = ConsumeType.TRUE.create();

    /**
     * 解析条件定义
     */
    public static ICondition parseCondition(ConditionDef def) {
        AbstractCondition newCondition = (AbstractCondition) def.getType().create();
        newCondition.parse(def.getValue());
        return newCondition;
    }

    /**
     * 解析条件定义
     */
    public static ICondition parseCondition(ConditionDef[] defs) {
        if (defs == null || defs.length == 0) {
            return trueCondition;
        }
        if (defs.length == 1) { // 减少小对象
            return parseCondition(defs[0]);
        }

        AndCondition andCondition = (AndCondition) ConditionType.AND.create();
        for (ConditionDef def : defs) {
            if (def == null) {
                continue;
            }

            andCondition.addCondition((AbstractCondition) parseCondition(def));
        }
        return andCondition;
    }

    /**
     * 解析消耗定义
     */
    public static IConsume parseConsume(ConsumeDef def) {
        AbstractConsume newConsume = (AbstractConsume) def.getType().create();
        newConsume.parse(def.getValue());
        return newConsume;
    }

    /**
     * 解析消耗定义
     */
    public static IConsume parseConsume(ConsumeDef[] defs) {
        if (defs == null || defs.length == 0) {
            return trueConsume;
        }
        if (defs.length == 1) { // 减少小对象
            return parseConsume(defs[0]);
        }

        AndConsume andConsume = (AndConsume) ConsumeType.AND.create();
        for (ConsumeDef def : defs) {
            if (def == null) {
                continue;
            }

            andConsume.addConsume((AbstractConsume) parseConsume(def));
        }
        return andConsume;
    }
}
