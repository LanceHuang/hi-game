package com.lance.game.module.buff.config;

/**
 * buff配置
 *
 * @author Lance
 */
public class BuffConfig {

    /** buffId */
    private int    id;
    /** 类型 */
    private int    type;
    /** 持续时间 */
    private long   duration;
    /** 配置值 */
    private String value;

//    private AttributeExpression attributeExpression;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
