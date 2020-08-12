package com.lance.game.demo.core.condition;

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

    private Class<? extends AbstractCondition> clazz;

    ConditionType(Class<? extends AbstractCondition> clazz) {
        this.clazz = clazz;
    }

    /**
     * 创建条件
     */
    public AbstractCondition create() {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("创建条件失败：" + this.name());
    }

    //================================= Getter/Setter ==================================

    public Class<? extends AbstractCondition> getClazz() {
        return clazz;
    }
}
