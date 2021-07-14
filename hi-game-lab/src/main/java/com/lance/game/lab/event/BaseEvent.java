package com.lance.game.lab.event;

/**
 * 基础事件
 *
 * @author Lance
 * @since 2021/7/14
 */
public abstract class BaseEvent implements Event {

    /** 事件源 */
    private Object source;

    /** 创建时间 */
    private long createTime;

    public BaseEvent(Object source) {
        this.source = source;
        this.createTime = System.currentTimeMillis();
    }

    @Override
    public int modValue() {
        return (int) createTime;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
