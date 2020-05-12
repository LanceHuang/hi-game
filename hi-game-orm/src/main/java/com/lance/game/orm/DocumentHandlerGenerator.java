package com.lance.game.orm;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * DocumentHandler生成工具，自动生成实体对应的DocumentHandler
 *
 * @author Lance
 */
public class DocumentHandlerGenerator {

    private static final ClassPool classPool = ClassPool.getDefault();

    /**
     * 生成代理类
     *
     * @param clazz 代理接口
     */
    public static Class<?> generateClass(Class<?> clazz) throws NotFoundException, CannotCompileException {
        if (clazz == null || clazz.isInterface()) { // todo 抽象与final都过滤掉
            return null;
        }

        // 1. 生成子类
        CtClass enhanceClass = classPool.makeClass(clazz.getName() + "DocumentHandler");

        // 2. 实现接口
        enhanceClass.addInterface(classPool.getCtClass(DocumentHandler.class.getName()));


        // 3. 读取成员变量
        Field[] declaredFields = clazz.getDeclaredFields();
        FieldInfo[] fieldInfos = new FieldInfo[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            // todo static final 类型过滤掉
            Field f = declaredFields[i];
            fieldInfos[i] = new FieldInfo(f.getName(), f.getType());
        }

        // 4. 实现方法
        generateParseMethod(enhanceClass, clazz, fieldInfos);
        generateHandleMethod(enhanceClass, clazz, fieldInfos);

        return enhanceClass.toClass();
    }


    private static void generateParseMethod(CtClass enhanceClass, Class<?> clazz, FieldInfo[] fieldInfos) throws NotFoundException, CannotCompileException {
        CtMethod ctMethod = new CtMethod(
                classPool.getCtClass(Document.class.getName()),
                "parse",
                new CtClass[]{classPool.getCtClass(clazz.getName())},
                enhanceClass);
        ctMethod.setModifiers(Modifier.PUBLIC);

        StringBuilder methodBody = new StringBuilder();
        methodBody.append('{');
        methodBody.append("org.bson.Document doc = new org.bson.Document();");
        for (FieldInfo fieldInfo : fieldInfos) {
            methodBody.append(generateParseSentence(fieldInfo));
//                    String.format("doc.append(\"%s\", $1.%s());", fieldInfo.fieldName, generateGetterMethodName(fieldInfo)));
        }
        methodBody.append("return doc;");
        methodBody.append('}');
        ctMethod.setBody(methodBody.toString());

        enhanceClass.addMethod(ctMethod);
    }

    private static String generateParseSentence(FieldInfo fieldInfo) {
        Class<?> fieldType = fieldInfo.fieldType;

        // 生成方法名
        String methodPrefix;
        if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
            methodPrefix = "is";
        } else {
            methodPrefix = "get";
        }
        String fieldName = fieldInfo.fieldName;
        String getterMethodName = methodPrefix + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

        // 将原始类型转换成对象类型
        String template;
        if (fieldType.isPrimitive()) {
            template = "doc.append(\"%s\", %s.valueOf($1.%s()));";
            String primitiveTypeName = null;
            if (fieldType.equals(byte.class)) {
                primitiveTypeName = Integer.class.getName();
            } else if (fieldType.equals(short.class)) {
                primitiveTypeName = Integer.class.getName();
            } else if (fieldType.equals(int.class)) {
                primitiveTypeName = Integer.class.getName();
            } else if (fieldType.equals(long.class)) {
                primitiveTypeName = Integer.class.getName();
            } else if (fieldType.equals(float.class)) {
                primitiveTypeName = Integer.class.getName();
            } else if (fieldType.equals(double.class)) {
                primitiveTypeName = Integer.class.getName();
            } else if (fieldType.equals(char.class)) {
                primitiveTypeName = Integer.class.getName();
            }

            // 生成语句
            return String.format(template, fieldInfo.fieldName, primitiveTypeName, getterMethodName);
        } else {
            template = "doc.append(\"%s\", $1.%s());";

            // 生成语句
            return String.format(template, fieldInfo.fieldName, getterMethodName);
        }
    }


    private static void generateHandleMethod(CtClass enhanceClass, Class<?> clazz, FieldInfo[] fieldInfos) throws NotFoundException, CannotCompileException {
        CtMethod ctMethod = new CtMethod(
                classPool.getCtClass(clazz.getName()),
                "handle",
                new CtClass[]{classPool.getCtClass(Document.class.getName())},
                enhanceClass);
        ctMethod.setModifiers(Modifier.PUBLIC);

        StringBuilder methodBody = new StringBuilder();
        methodBody.append('{');
        methodBody.append(String.format("%s obj = new %s();", clazz.getName(), clazz.getName()));
        for (FieldInfo fieldInfo : fieldInfos) {
            methodBody.append(generateHandleSentence(fieldInfo));
        }
        methodBody.append("return obj;");
        methodBody.append('}');
        ctMethod.setBody(methodBody.toString());

        enhanceClass.addMethod(ctMethod);
    }

    private static String generateHandleSentence(FieldInfo fieldInfo) {
        Class<?> fieldType = fieldInfo.fieldType;

        // 生成方法名
        String methodPrefix = "set";
        String fieldName = fieldInfo.fieldName;
        String setterMethodName = methodPrefix + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

        // 将原始类型转换成对象类型
        String template = "obj.%s($1.%s(\"%s\"));";
        String getDocMethodName = null;
        if (fieldType.equals(String.class)) {
            getDocMethodName = "getString";
        } else if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
            getDocMethodName = "getInteger";
        } else if (fieldType.equals(long.class)) {
            getDocMethodName = "getLong";
        }

        // 生成语句
        return String.format(template, setterMethodName, getDocMethodName, fieldInfo.fieldName);
    }

    public static class FieldInfo {
        public String fieldName;
        public Class<?> fieldType;

        public FieldInfo(String fieldName, Class<?> fieldType) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
        }
    }

}
