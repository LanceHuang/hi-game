package com.lance.game.net.schema;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.lance.game.net.annotation.Message;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

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

        // todo 如果有自定义类型，该怎么处理？

        // 获取消息id
        int messageId = messageAnnotation.value();

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
        ReflectionUtils.doWithFields(clazz, field -> {
            Class<?> fieldType = field.getType();
            String capitalizeName = StringUtils.capitalize(field.getName());
            if (!isSupportedType(fieldType)) {
                throw new IllegalArgumentException("Unsupported type: " + fieldType);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                sb.append("size += 1;");
            } else if (fieldType == int.class || fieldType == Integer.class) {
                sb.append(String.format("size += com.google.protobuf.CodedOutputStream.computeInt32SizeNoTag(bean.get%s());", capitalizeName));
            } else if (fieldType == long.class || fieldType == Long.class) {
                sb.append(String.format("size += com.google.protobuf.CodedOutputStream.computeInt64SizeNoTag(bean.get%s());", capitalizeName));
            } else if (fieldType == float.class || fieldType == Float.class) {
                sb.append("size += 4;");
            } else if (fieldType == double.class || fieldType == Double.class) {
                sb.append("size += 8;");
            } else if (fieldType == String.class) {
                sb.append(String.format("size += com.google.protobuf.CodedOutputStream.computeStringSizeNoTag(bean.get%s());", capitalizeName));
            } else {
                // todo
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
                classPool.get(new String[]{CodedOutputStream.class.getName(), Object.class.getName()}),
                enhanceClass);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{ %s bean = (%s) $2;", clazz.getName(), clazz.getName()));
        ReflectionUtils.doWithFields(clazz, field -> {
            Class<?> fieldType = field.getType();
            String capitalizeName = StringUtils.capitalize(field.getName());
            if (!isSupportedType(fieldType)) {
                throw new IllegalArgumentException("Unsupported type: " + fieldType);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                sb.append(String.format("$1.writeBoolNoTag(bean.is%s());", capitalizeName));
            } else if (fieldType == int.class || fieldType == Integer.class) {
                sb.append(String.format("$1.writeInt32NoTag(bean.get%s());", capitalizeName));
            } else if (fieldType == long.class || fieldType == Long.class) {
                sb.append(String.format("$1.writeInt64NoTag(bean.get%s());", capitalizeName));
            } else if (fieldType == float.class || fieldType == Float.class) {
                sb.append(String.format("$1.writeFloatNoTag(bean.get%s());", capitalizeName));
            } else if (fieldType == double.class || fieldType == Double.class) {
                sb.append(String.format("$1.writeDoubleNoTag(bean.get%s());", capitalizeName));
            } else if (fieldType == String.class) {
                sb.append(String.format("$1.writeStringNoTag(bean.get%s());", capitalizeName));
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
                classPool.get(new String[]{CodedInputStream.class.getName()}),
                enhanceClass);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{ %s bean = new %s();", clazz.getName(), clazz.getName()));
        ReflectionUtils.doWithFields(clazz, field -> {
            Class<?> fieldType = field.getType();
            String capitalizeName = StringUtils.capitalize(field.getName());
            if (!isSupportedType(fieldType)) {
                throw new IllegalArgumentException("Unsupported type: " + fieldType);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                sb.append(String.format("bean.set%s($1.readBool());", capitalizeName));
            } else if (fieldType == int.class || fieldType == Integer.class) {
                sb.append(String.format("bean.set%s($1.readInt32());", capitalizeName));
            } else if (fieldType == long.class || fieldType == Long.class) {
                sb.append(String.format("bean.set%s($1.readInt64());", capitalizeName));
            } else if (fieldType == float.class || fieldType == Float.class) {
                sb.append(String.format("bean.set%s($1.readFloat());", capitalizeName));
            } else if (fieldType == double.class || fieldType == Double.class) {
                sb.append(String.format("bean.set%s($1.readDouble());", capitalizeName));
            } else if (fieldType == String.class) {
                sb.append(String.format("bean.set%s($1.readStringRequireUtf8());", capitalizeName));
            } else {
                // todo
            }
        });
        sb.append("return bean; }");

        ctMethod.setBody(sb.toString());
        enhanceClass.addMethod(ctMethod);
    }

    private static boolean isSupportedType(Class<?> clazz) {
        return clazz != byte.class && clazz != Byte.class &&
                clazz != char.class && clazz != Character.class &&
                clazz != short.class && clazz != Short.class;
    }
}
