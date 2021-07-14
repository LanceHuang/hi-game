package com.lance.game.lab.event;

/**
 * @author Lance
 * @since 2021/7/14
 */
public abstract class BaseEvent implements Event {

    private Object source;

    private long createTime;

    public BaseEvent(Object source) {
        this.source = source;
        this.createTime = System.currentTimeMillis();
    }

    @Override
    public int getId() {
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
