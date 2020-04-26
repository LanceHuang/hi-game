package com.lance.game.demo.module.activity.constant;

/**
 * 活动类型
 *
 * @author Lance
 * @since 2020/1/14 12:33
 */
public enum ActivityType {

    /** 测试活动 */
    TEST(10000),
    ;

    /** 活动id */
    private int id;

    ActivityType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
