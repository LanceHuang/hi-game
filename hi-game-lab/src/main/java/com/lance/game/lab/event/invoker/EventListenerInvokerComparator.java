package com.lance.game.lab.event.invoker;

import java.util.Comparator;
import java.util.List;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class EventListenerInvokerComparator implements Comparator<EventListenerInvoker> {

    @Override
    public int compare(EventListenerInvoker o1, EventListenerInvoker o2) {
        EventListenerInvokerProxy proxy1 = (EventListenerInvokerProxy) o1;
        EventListenerInvokerProxy proxy2 = (EventListenerInvokerProxy) o2;
        AbstractEventListenerInvoker ao1 = (AbstractEventListenerInvoker) proxy1.getInvoker();
        AbstractEventListenerInvoker ao2 = (AbstractEventListenerInvoker) proxy2.getInvoker();

        // 异步任务优先
        if (ao1.isAsync() && !ao2.isAsync()) {
            return -1;
        } else if (!ao1.isAsync() && ao2.isAsync()) {
            return 1;
        } else {
            return ao2.getOrder() - ao1.getOrder();
        }
    }

    /**
     * 排序
     */
    public static void sort(List<EventListenerInvoker> list) {
        if (list == null) {
            return;
        }

        if (list.size() > 1) {
            list.sort(getInstance());
        }
    }

    public static EventListenerInvokerComparator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static class SingletonHolder {
        private static final EventListenerInvokerComparator INSTANCE = new EventListenerInvokerComparator();
    }
}
