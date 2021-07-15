package com.lance.game.lab.event.invoker;

import com.lance.game.lab.event.Event;
import com.lance.game.lab.event.annotation.Async;
import com.lance.game.lab.event.annotation.EventListener;
import com.lance.game.lab.event.annotation.Order;
import com.lance.game.lab.event.executor.EventListenerExecutor;
import com.lance.game.lab.event.filter.AbstractEventFilter;
import com.lance.game.lab.event.filter.AnnotationEventFilter;
import com.lance.game.lab.event.filter.AssignableEventFilter;
import com.lance.game.lab.event.filter.EventFilter;
import com.lance.game.lab.event.filter.RegexEventFilter;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Lance
 * @since 2021/7/14
 */
public class EventListenerInvokerFactory {

    public static final String SYNC_EXECUTOR = "syncEventListenerExecutor";

    public static final String ASYNC_EXECUTOR = "asyncEventListenerExecutor";

    @Autowired
    private final Map<String, EventListenerExecutor> executorMap = new HashMap<>();

    /**
     * 创建事件监听器
     *
     * @param bean   监听bean
     * @param method 监听方法
     * @return 事件监听器
     */
    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method) {
        Order orderAnnotation = method.getAnnotation(Order.class);
        Async asyncAnnotation = method.getAnnotation(Async.class);

        int order = orderAnnotation == null ? 0 : orderAnnotation.value();
        boolean async = asyncAnnotation != null;

        // 获取执行器
        String executorName = getExecutorName(asyncAnnotation);

        // 创建过滤器
        EventFilter eventFilter = createEventFilter(bean, method);

        // 创建代理对象
        return doCreateEventListenerInvoker(bean, method, order, async, executorName, eventFilter);
    }

    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method, Class<?>[] eventTypes) {
        return doCreateEventListenerInvoker(bean, method, 0, false, SYNC_EXECUTOR, new AssignableEventFilter(eventTypes));
    }

    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method, Class<?>[] eventTypes, int order) {
        return doCreateEventListenerInvoker(bean, method, order, false, SYNC_EXECUTOR, new AssignableEventFilter(eventTypes));
    }

    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method, Class<?>[] eventTypes, boolean async) {
        String executorName = async ? ASYNC_EXECUTOR : SYNC_EXECUTOR;
        return doCreateEventListenerInvoker(bean, method, 0, async, executorName, new AssignableEventFilter(eventTypes));
    }

    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method, Class<?>[] eventTypes, int order, boolean async) {
        String executorName = async ? ASYNC_EXECUTOR : SYNC_EXECUTOR;
        return doCreateEventListenerInvoker(bean, method, order, async, executorName, new AssignableEventFilter(eventTypes));
    }

    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method, Class<?>[] eventTypes, int order, boolean async, String executorName) {
        return doCreateEventListenerInvoker(bean, method, order, async, executorName, new AssignableEventFilter(eventTypes));
    }

    private EventListenerInvoker doCreateEventListenerInvoker(Object bean, Method method, int order, boolean async, String executorName, EventFilter eventFilter) {
        EventListenerExecutor executor = executorMap.get(executorName);
        if (executor == null) {
            throw new IllegalArgumentException("No executor specified: " + executorName);
        }

        // 创建代理类
        AbstractEventListenerInvoker invoker = enhanceInvoker(bean, method);
        invoker.setBean(bean);
        invoker.setMethod(method);
        invoker.setOrder(order);
        invoker.setAsync(async);
        invoker.setFilter(eventFilter);
        return new EventListenerInvokerProxy(executor, invoker);
    }

    private String getExecutorName(Async asyncAnnotation) {
        if (asyncAnnotation == null) {
            return SYNC_EXECUTOR;
        }
        if (asyncAnnotation.value().isEmpty()) {
            return ASYNC_EXECUTOR;
        }
        return asyncAnnotation.value();
    }

    /**
     * 创建事件过滤器
     *
     * @param bean   监听bean
     * @param method 监听方法
     * @return 事件过滤器
     */
    private EventFilter createEventFilter(Object bean, Method method) {
        EventListener eventListener = method.getAnnotation(EventListener.class);
        switch (eventListener.type()) {
            case ASSIGNABLE:
                return new AssignableEventFilter(bean, method);
            case ANNOTATION:
                return new AnnotationEventFilter(bean, method);
            case REGEX:
                return new RegexEventFilter(bean, method);
            case CUSTOM:
                return createCustomEventFilter(bean, method);
            default:
                throw new IllegalArgumentException("Failed to create event filter: " + bean.getClass().getName() + "#" + method.getName());
        }
    }

    private EventFilter createCustomEventFilter(Object bean, Method method) {
        EventListener eventListener = method.getAnnotation(EventListener.class);
        Class<?>[] eventListenerValue = eventListener.value();
        if (eventListenerValue.length == 0) {
            throw new IllegalArgumentException("EventListener.value cannot be empty: " + bean.getClass().getName() + "#" + method.getName());
        }
        Class<?> customFilterClass = eventListenerValue[0];
        if (!EventFilter.class.isAssignableFrom(customFilterClass)) {
            throw new IllegalArgumentException("Unexpected custom event filter: " + bean.getClass().getName() + "#" + method.getName()
                    + " " + customFilterClass.getName());
        }

        try {
            if (AbstractEventFilter.class.isAssignableFrom(customFilterClass)) {
                Constructor<?> c = customFilterClass.getConstructor(Object.class, Method.class);
                return (EventFilter) c.newInstance(bean, method);
            } else {
                return (EventFilter) customFilterClass.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Failed to create custom event filter: " + bean.getClass().getName() + "#" + method.getName());
        }
    }


    private static final ClassPool CLASS_POOL = ClassPool.getDefault();

    private static final AtomicInteger INDEX = new AtomicInteger(0);

    /**
     * 创建代理对象
     *
     * @param bean   被代理对象
     * @param method 被代理方法
     * @return 代理对象
     */
    public static AbstractEventListenerInvoker enhanceInvoker(Object bean, Method method) {
        Parameter[] parameters = method.getParameters();
        Class<?> parameterClass = parameters[0].getType();

        try {
            Class<?> clazz = bean.getClass();
            String methodName = method.getName();
            CtClass enhanceClass = buildInvoker();

            // 监听方法
            CtMethod invokerMethod = new CtMethod(
                    CtClass.voidType,
                    "invokeListener",
                    CLASS_POOL.get(new String[]{Event.class.getCanonicalName()}),
                    enhanceClass
            );
            invokerMethod.setBody("{ ((" + clazz.getCanonicalName() + ") getBean())." + methodName + "((" + parameterClass.getCanonicalName() + ")$1);}");
            enhanceClass.addMethod(invokerMethod);

            // 创建代理对象
            return (AbstractEventListenerInvoker) enhanceClass.toClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to enhance invoker: " + bean.getClass().getName());
        }
    }

    private static CtClass buildInvoker() throws Exception {
        Class<?> superClass = AbstractEventListenerInvoker.class;
        CtClass enhanceClass = CLASS_POOL.makeClass("EventListenerInvokerEnhance" + INDEX.getAndIncrement());
        enhanceClass.setSuperclass(CLASS_POOL.get(superClass.getCanonicalName()));
        return enhanceClass;
    }

}
