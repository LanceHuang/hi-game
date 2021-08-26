package com.lance.game.event;

/**
 * 事件
 *
 * @author Lance
 * @since 2021/7/14
 */
public interface Event {

    /**
     * 获取事件标识
     *
     * @return 事件标识
     */
    default int modValue() {
        return 0;
    }

    /**
     * 格式化日志
     *
     * @return 日志
     */
    default String logString() {
        return this.getClass().getName();
    }
}
