package com.lance.game.demo.log;

/**
 * 日志编码
 *
 * @author Lance
 */
public enum LogCode {

    TEST(10000, "测试"),

    /** buff */
    BUFF_ADD(10001, "添加buff"),
    BUFF_REMOVE(10002, "移除buff"),
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
