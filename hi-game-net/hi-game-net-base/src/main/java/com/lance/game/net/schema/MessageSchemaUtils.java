package com.lance.game.net.schema;

import com.lance.game.net.annotation.Message;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Modifier;

/**
 * schema工具类
 *
 * @author Lance
 * @since 2021/4/28
 */
public class MessageSchemaUtils {

    private static final ClassPool classPool = ClassPool.getDefault();

    /**
     * 根据消息类型，生成schema
     *
     * @param clazz 消息类
     * @return schema
     */
    public static MessageSchema enhance(Class<?> clazz) throws Exception {
        if (clazz == null || clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
            throw new IllegalArgumentException("Illegal message class: " + clazz);
        }

        Message messageAnnotation = clazz.getAnnotation(Message.class);
        if (messageAnnotation == null) {
            throw new IllegalArgumentException("Class must annotated with annotation Message: " + clazz);
        }

        // 消息id
        int messageId = messageAnnotation.value();
        CtClass schemaClass = classPool.getCtClass(MessageSchema.class.getName());
        CtClass enhanceClass = classPool.makeClass(clazz.getName() + "$Schema", schemaClass);

        // todo 如果有继承关系，该怎么处理？

        // getId
        CtMethod getIdMethod = new CtMethod(
                classPool.get(int.class.getName()),
                "getId",
                new CtClass[0],
                enhanceClass);
        getIdMethod.setBody("{ return " + messageId + "; }");
        enhanceClass.addMethod(getIdMethod);

        // getSerializedSize
        CtMethod getSerializedSizeMethod = new CtMethod(
                classPool.get(int.class.getName()),
                "getSerializedSize",
                classPool.get(new String[]{Object.class.getName()}),
                enhanceClass);
        getSerializedSizeMethod.setBody("{ return " + messageId + "; }");
        enhanceClass.addMethod(getSerializedSizeMethod);

        // 创建schema对象
        Class<?> resultClass = enhanceClass.toClass();
        return (MessageSchema) resultClass.newInstance();
    }
}
