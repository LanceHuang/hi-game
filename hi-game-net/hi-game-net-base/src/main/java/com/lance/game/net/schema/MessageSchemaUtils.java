package com.lance.game.net.schema;

import io.netty.buffer.ByteBuf;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Modifier;

/**
 * schema工具类
 *
 * @author Lance
 * @since 2021/4/28
 */
@Slf4j
public class MessageSchemaUtils {

    private static final ClassPool classPool = ClassPool.getDefault();

    /**
     * 根据消息类型，生成schema
     *
     * @param clazz 消息类
     * @return schema
     */
    public static MessageSchema enhance(Class<?> clazz, int messageId) {
        if (clazz == null || clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
            throw new IllegalArgumentException("Illegal message class: " + clazz);
        }

        // todo 如果有自定义类型，该怎么处理？

        try {
            // 生成方法体
            CtClass schemaClass = classPool.getCtClass(MessageSchema.class.getName());
            CtClass enhanceClass = classPool.makeClass(clazz.getName() + "$Schema", schemaClass);
            generateGetIdMethod(enhanceClass, clazz, messageId);
            generateGetSerializedSizeMethod(enhanceClass, clazz);
            generateSerializeMethod(enhanceClass, clazz);
            generateDeserializeMethod(enhanceClass, clazz);

            // 创建schema对象
            Class<?> resultClass = enhanceClass.toClass();
            return (MessageSchema) resultClass.newInstance();
        } catch (Exception e) {
            log.error("Failed to enhance message class: {}", clazz.getName(), e);
        }
        return null;
    }

    private static void generateGetIdMethod(CtClass enhanceClass, Class<?> clazz, int messageId) throws Exception {
        CtMethod ctMethod = new CtMethod(
                classPool.get(int.class.getName()),
                "getId",
                new CtClass[0],
                enhanceClass);
        ctMethod.setBody("{ return " + messageId + "; }");
        enhanceClass.addMethod(ctMethod);
    }

    private static void generateGetSerializedSizeMethod(CtClass enhanceClass, Class<?> clazz) throws Exception {
        CtMethod ctMethod = new CtMethod(
                classPool.get(int.class.getName()),
                "getSerializedSize",
                classPool.get(new String[]{Object.class.getName()}),
                enhanceClass);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{ %s bean = (%s) $1;", clazz.getName(), clazz.getName()));
        sb.append("int size = 0;");
        sb.append("size += com.lance.game.net.util.ByteBufUtils.computeInt(getId());");
        ReflectionUtils.doWithFields(clazz, field -> {
            Class<?> fieldType = field.getType();
            String capitalizeName = StringUtils.capitalize(field.getName());
            if (isNotSupportedType(fieldType)) {
                throw new IllegalArgumentException("Unsupported type: " + fieldType);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                sb.append(String.format("size += com.lance.game.net.util.ByteBufUtils.computeBoolean(bean.get%s());", capitalizeName));
            } else if (fieldType == int.class || fieldType == Integer.class) {
                sb.append(String.format("size += com.lance.game.net.util.ByteBufUtils.computeInt(bean.get%s());", capitalizeName));
            } else if (fieldType == long.class || fieldType == Long.class) {
                sb.append(String.format("size += com.lance.game.net.util.ByteBufUtils.computeLong(bean.get%s());", capitalizeName));
            } else if (fieldType == float.class || fieldType == Float.class) {
                sb.append(String.format("size += com.lance.game.net.util.ByteBufUtils.computeFloat(bean.get%s());", capitalizeName));
            } else if (fieldType == double.class || fieldType == Double.class) {
                sb.append(String.format("size += com.lance.game.net.util.ByteBufUtils.computeDouble(bean.get%s());", capitalizeName));
            } else if (fieldType == String.class) {
                sb.append(String.format("size += com.lance.game.net.util.ByteBufUtils.computeString(bean.get%s());", capitalizeName));
            } else {
                // todo 自定义类型、集合、映射
            }
        });
        sb.append("return size; }");

        ctMethod.setBody(sb.toString());
        enhanceClass.addMethod(ctMethod);
    }

    private static void generateSerializeMethod(CtClass enhanceClass, Class<?> clazz) throws Exception {
        CtMethod ctMethod = new CtMethod(
                classPool.get(void.class.getName()),
                "serialize",
                classPool.get(new String[]{ByteBuf.class.getName(), Object.class.getName()}),
                enhanceClass);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{ %s bean = (%s) $2;", clazz.getName(), clazz.getName()));
        sb.append("com.lance.game.net.util.ByteBufUtils.writeInt($1, getId());");
        ReflectionUtils.doWithFields(clazz, field -> {
            Class<?> fieldType = field.getType();
            String capitalizeName = StringUtils.capitalize(field.getName());
            if (isNotSupportedType(fieldType)) {
                throw new IllegalArgumentException("Unsupported type: " + fieldType);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                sb.append(String.format("com.lance.game.net.util.ByteBufUtils.writeBoolean($1, bean.is%s());", capitalizeName));
            } else if (fieldType == int.class || fieldType == Integer.class) {
                sb.append(String.format("com.lance.game.net.util.ByteBufUtils.writeInt($1, bean.get%s());", capitalizeName));
            } else if (fieldType == long.class || fieldType == Long.class) {
                sb.append(String.format("com.lance.game.net.util.ByteBufUtils.writeLong($1, bean.get%s());", capitalizeName));
            } else if (fieldType == float.class || fieldType == Float.class) {
                sb.append(String.format("com.lance.game.net.util.ByteBufUtils.writeFloat($1, bean.get%s());", capitalizeName));
            } else if (fieldType == double.class || fieldType == Double.class) {
                sb.append(String.format("com.lance.game.net.util.ByteBufUtils.writeDouble($1, bean.get%s());", capitalizeName));
            } else if (fieldType == String.class) {
                sb.append(String.format("com.lance.game.net.util.ByteBufUtils.writeString($1, bean.get%s());", capitalizeName));
            } else {
                // todo
            }
        });
        sb.append("}");

        ctMethod.setBody(sb.toString());
        enhanceClass.addMethod(ctMethod);
    }

    private static void generateDeserializeMethod(CtClass enhanceClass, Class<?> clazz) throws Exception {
        CtMethod ctMethod = new CtMethod(
                classPool.get(Object.class.getName()),
                "deserialize",
                classPool.get(new String[]{ByteBuf.class.getName()}),
                enhanceClass);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{ %s bean = new %s();", clazz.getName(), clazz.getName()));
        sb.append("com.lance.game.net.util.ByteBufUtils.readInt($1);");
        ReflectionUtils.doWithFields(clazz, field -> {
            Class<?> fieldType = field.getType();
            String capitalizeName = StringUtils.capitalize(field.getName());
            if (isNotSupportedType(fieldType)) {
                throw new IllegalArgumentException("Unsupported type: " + fieldType);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                sb.append(String.format("bean.set%s(com.lance.game.net.util.ByteBufUtils.readBoolean($1));", capitalizeName));
            } else if (fieldType == int.class || fieldType == Integer.class) {
                sb.append(String.format("bean.set%s(com.lance.game.net.util.ByteBufUtils.readInt($1));", capitalizeName));
            } else if (fieldType == long.class || fieldType == Long.class) {
                sb.append(String.format("bean.set%s(com.lance.game.net.util.ByteBufUtils.readLong($1));", capitalizeName));
            } else if (fieldType == float.class || fieldType == Float.class) {
                sb.append(String.format("bean.set%s(com.lance.game.net.util.ByteBufUtils.readFloat($1));", capitalizeName));
            } else if (fieldType == double.class || fieldType == Double.class) {
                sb.append(String.format("bean.set%s(com.lance.game.net.util.ByteBufUtils.readDouble($1));", capitalizeName));
            } else if (fieldType == String.class) {
                sb.append(String.format("bean.set%s(com.lance.game.net.util.ByteBufUtils.readString($1));", capitalizeName));
            } else {
                // todo
            }
        });
        sb.append("return bean; }");

        ctMethod.setBody(sb.toString());
        enhanceClass.addMethod(ctMethod);
    }

    private static boolean isNotSupportedType(Class<?> clazz) {
        return clazz == byte.class || clazz == Byte.class ||
                clazz == char.class || clazz == Character.class ||
                clazz == short.class || clazz == Short.class;
    }
}
