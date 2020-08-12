package com.lance.game.demo.core.util;

import com.lance.game.demo.core.condition.AbstractCondition;
import com.lance.game.demo.core.condition.AndCondition;
import com.lance.game.demo.core.condition.ConditionType;
import com.lance.game.demo.core.model.ConditionDef;
import com.lance.game.demo.core.ICondition;

/**
 * @author Lance
 */
public class ConditionUtils {

    private static final ICondition trueCondition = ConditionType.TRUE.create();

    /**
     * 解析条件定义
     */
    public static ICondition parseCondition(ConditionDef def) {
        AbstractCondition newCondition = def.getType().create();
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
}
