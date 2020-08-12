package com.lance.game.demo.core.condition;

import com.lance.game.demo.core.ICondition;

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
    /** 等级大于等于X级 */
    LEVEL(LevelCondition.class),
    /** 等级大于等于X级，小于等于Y级 */
    LEVEL_RANGE(LevelRangeCondition.class);

    private Class<? extends ICondition> clazz;

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

    //================================= Getter/Setter ==================================

    public Class<? extends ICondition> getClazz() {
        return clazz;
    }
}
