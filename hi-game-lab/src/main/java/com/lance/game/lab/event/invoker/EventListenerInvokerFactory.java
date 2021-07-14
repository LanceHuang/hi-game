package com.lance.game.lab.event.invoker;

import com.lance.game.lab.event.Event;
import com.lance.game.lab.event.annotation.Async;
import com.lance.game.lab.event.annotation.Order;
import com.lance.game.lab.event.executor.EventListenerExecutor;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
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

    private ClassPool classPool = ClassPool.getDefault();

    private AtomicInteger index = new AtomicInteger(0);

    @Autowired
    private Map<String, EventListenerExecutor> executorMap = new HashMap<>();

    /**
     * 创建事件监听器
     *
     * @param bean   监听bean
     * @param method 监听方法
     * @return 事件监听器
     */
    public EventListenerInvoker createEventListenerInvoker(Object bean, Method method, Class<?> parameterClass) {
        Order orderAnnotation = method.getAnnotation(Order.class);
        Async asyncAnnotation = method.getAnnotation(Async.class);

        int order = orderAnnotation == null ? 0 : orderAnnotation.value();
        boolean async = asyncAnnotation != null;

        String executorName = getExecutorName(asyncAnnotation);
        EventListenerExecutor executor = executorMap.get(executorName);
        if (executor == null) {
            throw new IllegalArgumentException("No executor specified: " + executorName);
        }

        // TODO: 2021/7/14 filter?

        AbstractEventListenerInvoker invoker = enhanceInvoker(bean, method, parameterClass);
        invoker.setBean(bean);
        invoker.setMethod(method);
        invoker.setOrder(order);
        invoker.setAsync(async);
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

    private AbstractEventListenerInvoker enhanceInvoker(Object bean, Method method, Class<?> parameterClass) {
        try {
            Class<?> clazz = bean.getClass();
            String methodName = method.getName();
            CtClass enhanceClass = buildInvoker();

            // 监听方法
            CtMethod invokerMethod = new CtMethod(
                    CtClass.voidType,
                    "invokeListener",
                    classPool.get(new String[]{Event.class.getCanonicalName()}),
                    enhanceClass
            );
            invokerMethod.setBody("{ ((" + clazz.getCanonicalName() + ") getBean())." + methodName + "((" + parameterClass.getCanonicalName() + ")$1);}");
            enhanceClass.addMethod(invokerMethod);

            // 创建代理对象
            return (AbstractEventListenerInvoker) enhanceClass.toClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Failed to enhance invoker: " + bean.getClass().getName());
    }

    private CtClass buildInvoker() throws Exception {
        Class<?> superClass = AbstractEventListenerInvoker.class;
        CtClass enhanceClass = classPool.makeClass("EventListenerInvokerEnhance" + index.getAndIncrement());
        enhanceClass.setSuperclass(classPool.get(superClass.getCanonicalName()));
        return enhanceClass;
    }
}
