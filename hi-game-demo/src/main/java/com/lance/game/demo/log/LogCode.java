package com.lance.game.demo.log;

/**
 * 日志编码
 *
 * @author Lance
 */
public enum LogCode {

    TEST(10000, "测试"),

    /** buff */
    BUFF_ADD(10100, "添加buff"),
    BUFF_REMOVE(10101, "移除buff"),

    /** 坐骑 */
    HORSE_RIDE(10200, "上坐骑"),
    ;

    LogCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;

    private String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
