package com.lance.game.demo.condition;

import com.lance.game.demo.condition.impl.AndCondition;

/**
 * 条件工具
 *
 * @author Lance
 * @since 2020/12/1
 */
public class ConditionUtils {

    /** 永真条件 */
    private static final ICondition trueCondition = ConditionType.TRUE.create();

    /**
     * 解析条件定义
     */
    public static ICondition parseCondition(ConditionDef def) {
        if (def == null || def.getType() == null) {
            return trueCondition;
        }

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

        // 减少小对象
        if (defs.length == 1) {
            return parseCondition(defs[0]);
        }

        AndCondition andCondition = (AndCondition) ConditionType.AND.create();
        for (ConditionDef def : defs) {
            andCondition.addCondition((AbstractCondition) parseCondition(def));
        }
        return andCondition;
    }
}
