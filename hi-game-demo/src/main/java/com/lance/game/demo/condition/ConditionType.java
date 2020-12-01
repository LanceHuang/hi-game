package com.lance.game.demo.condition;

import com.lance.game.demo.condition.impl.AndCondition;
import com.lance.game.demo.condition.impl.LevelCondition;
import com.lance.game.demo.condition.impl.LevelRangeCondition;
import com.lance.game.demo.condition.impl.TrueCondition;

/**
 * 条件类型
 *
 * @author Lance
 */
public enum ConditionType {

    /** 永真条件 */
    TRUE(TrueCondition.class),
    /** 与条件 */
    AND(AndCondition.class),
    /** 等级大于或等于X */
    LEVEL(LevelCondition.class),
    /** 等级在[X,Y]范围 */
    LEVEL_RANGE(LevelRangeCondition.class);

    private final Class<? extends ICondition> clazz;

    ConditionType(Class<? extends ICondition> clazz) {
        this.clazz = clazz;
    }

    /**
     * 创建条件
     */
    public ICondition create() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("创建条件失败：" + this.name());
    }
}
