package com.lance.game.orm;

import com.lance.game.orm.exception.UnsupportedTypeException;
import com.lance.game.orm.handler.DocumentHandler;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.SignatureAttribute;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * DocumentHandler生成工具，自动生成实体对应的DocumentHandler
 *
 * @author Lance
 */
public class DocumentHandlerProxyGenerator {

    private static final ClassPool classPool = ClassPool.getDefault();

    /**
     * 生成代理类
     *
     * @param clazz 代理接口
     */
    public static Class<?> generateClass(Class<?> clazz) throws NotFoundException, CannotCompileException {
        if (clazz == null || clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers()) || Modifier.isFinal(clazz.getModifiers())) {
            return null;
        }

        // 1. 生成子类
        CtClass enhanceClass = classPool.makeClass(clazz.getName() + "DocumentHandler$Proxy");

        // 2. 实现接口
        enhanceClass.addInterface(classPool.getCtClass(DocumentHandler.class.getName()));
        enhanceClass.setGenericSignature(new SignatureAttribute.TypeVariable("DocumentHandler<" + clazz.getName() + ">").encode());

        // 3. 读取成员变量
        Field[] declaredFields = clazz.getDeclaredFields();
        FieldInfo[] fieldInfos = new FieldInfo[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            Field f = declaredFields[i];
            if (Modifier.isFinal(f.getModifiers())) {
                continue;
            }
            fieldInfos[i] = new FieldInfo(f.getName(), f.getType());
        }

        // 4. 实现方法
        generateParseMethod(enhanceClass, clazz, fieldInfos);
        generateHandleMethod(enhanceClass, clazz, fieldInfos);

        return enhanceClass.toClass();
    }

    private static void generateParseMethod(CtClass enhanceClass, Class<?> clazz, FieldInfo[] fieldInfos) throws NotFoundException, CannotCompileException {
        // 涉及到反射的实现原理，暂时不处理Document parse(T obj)
        CtMethod ctMethod = new CtMethod(
                classPool.getCtClass(Document.class.getName()),
                "parse",
                new CtClass[]{classPool.getCtClass(Object.class.getName())},
                enhanceClass);
        ctMethod.setModifiers(Modifier.PUBLIC);

        StringBuilder methodBody = new StringBuilder();
        methodBody.append('{');
        methodBody.append(String.format("%s obj = (%s)$1;", clazz.getName(), clazz.getName()));
        methodBody.append("org.bson.Document doc = new org.bson.Document();");
        for (FieldInfo fieldInfo : fieldInfos) {
            methodBody.append(generateParseSentence(fieldInfo));
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
        if (fieldType.equals(boolean.class)) {
            methodPrefix = "is";
        } else {
            methodPrefix = "get";
        }
        String fieldName = fieldInfo.fieldName;
        String getterMethodName = methodPrefix + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

        // 将原始类型转换成对象类型
        String template;
        if (fieldType.isPrimitive()) {
            template = "doc.append(\"%s\", %s.valueOf(obj.%s()));";
            String primitiveTypeName;
            if (fieldType.equals(boolean.class)) {
                primitiveTypeName = Boolean.class.getName();
            } else if (fieldType.equals(int.class)) {
                primitiveTypeName = Integer.class.getName();
            } else if (fieldType.equals(long.class)) {
                primitiveTypeName = Long.class.getName();
            } else if (fieldType.equals(double.class)) {
                primitiveTypeName = Double.class.getName();
            } else {
                throw new UnsupportedTypeException("MongoDB 不支持该类型：" + fieldType);
            }

            // 生成语句
            return String.format(template, fieldInfo.fieldName, primitiveTypeName, getterMethodName);
        } else {
            template = "doc.append(\"%s\", obj.%s());";

            // 生成语句
            return String.format(template, fieldInfo.fieldName, getterMethodName);
        }
    }

    private static void generateHandleMethod(CtClass enhanceClass, Class<?> clazz, FieldInfo[] fieldInfos) throws NotFoundException, CannotCompileException {
        CtMethod ctMethod = new CtMethod(
                classPool.getCtClass(Object.class.getName()),
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
        String template = "obj.%s($1.%s(\"%s\")%s);";
        String getDocMethodName;
        String castPrimitive = "";
        if (fieldType.equals(String.class)) {
            getDocMethodName = "getString";
        } else if (fieldType.equals(int.class)) {
            getDocMethodName = "getInteger";
            castPrimitive = ".intValue()";
        } else if (fieldType.equals(Integer.class)) {
            getDocMethodName = "getInteger";
        } else if (fieldType.equals(long.class)) {
            getDocMethodName = "getLong";
            castPrimitive = ".longValue()";
        } else if (fieldType.equals(Long.class)) {
            getDocMethodName = "getLong";
        } else if (fieldType.equals(double.class)) {
            getDocMethodName = "getDouble";
            castPrimitive = ".doubleValue()";
        } else if (fieldType.equals(Double.class)) {
            getDocMethodName = "getDouble";
        } else if (fieldType.equals(boolean.class)) {
            getDocMethodName = "getBoolean";
            castPrimitive = ".booleanValue()";
        } else if (fieldType.equals(Boolean.class)) {
            getDocMethodName = "getBoolean";
        } else {
            throw new UnsupportedTypeException("MongoDB 不支持该类型：" + fieldType);
        }

        // 生成语句
        return String.format(template, setterMethodName, getDocMethodName, fieldInfo.fieldName, castPrimitive);
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
