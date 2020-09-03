package com.lance.game.event;

/**
 * 跨服事件发布者
 *
 * @author Lance
 */
public class CrossServerEventPublisher implements EventPublisher {

    @Override
    public void publishEvent(Object event) {
        // 方案一：遍历所有区服，并发送事件
        // 方案二：发送事件至中心服，再由中心服广播
        // 上面都要加发送队列
    }
}
