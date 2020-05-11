package com.lance.game.orm;

import com.lance.game.orm.annotation.Count;
import com.lance.game.orm.annotation.DeleteMany;
import com.lance.game.orm.annotation.DeleteOne;
import com.lance.game.orm.annotation.FindMany;
import com.lance.game.orm.annotation.FindOne;
import com.lance.game.orm.annotation.FindOneAndReplace;
import com.lance.game.orm.annotation.InsertMany;
import com.lance.game.orm.annotation.InsertOne;
import com.lance.game.orm.annotation.MongoDao;
import com.lance.game.orm.exception.IncorrectMethodParameterCountException;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @author Lance
 */
public class MongoDaoEnhancer {

    public static final ClassPool classPool = ClassPool.getDefault();

    /**
     * 生成代理类
     *
     * @param clazz 代理接口
     */
    public static Class<?> enhanceClass(Class<?> clazz) throws NotFoundException, CannotCompileException {
        if (clazz == null || !clazz.isInterface()) {
            return null;
        }

        MongoDao mongoDaoAnnotation = clazz.getDeclaredAnnotation(MongoDao.class);
        if (mongoDaoAnnotation == null) {
            return null;
        }
        String databaseName = mongoDaoAnnotation.databaseName(); // 数据库名称
        String collectionName = mongoDaoAnnotation.collectionName(); // 集合名称

        // 1. 生成子类
        CtClass enhanceClass = classPool.makeClass(clazz.getName() + "$Enhancer");

        // 2. 实现接口
        enhanceClass.addInterface(classPool.getCtClass(clazz.getName()));

        // 3. 实现方法
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(InsertOne.class)) {
                generateInsertOneMethod(enhanceClass, m, databaseName, collectionName);
            } else if (m.isAnnotationPresent(InsertMany.class)) {
                generateInsertManyMethod(enhanceClass, m, databaseName, collectionName);
            } else if (m.isAnnotationPresent(FindOne.class)) {
                generateFindOneMethod(enhanceClass, m, databaseName, collectionName);
            } else if (m.isAnnotationPresent(FindMany.class)) {
                generateFindManyMethod(enhanceClass, m, databaseName, collectionName);
            } else if (m.isAnnotationPresent(Count.class)) {
                generateCountMethod(enhanceClass, m, databaseName, collectionName);
            } else if (m.isAnnotationPresent(FindOneAndReplace.class)) {
                generateFindOneAndReplaceMethod(enhanceClass, m, databaseName, collectionName);
            } else if (m.isAnnotationPresent(DeleteOne.class)) {
                generateDeleteOneMethod(enhanceClass, m, databaseName, collectionName);
            } else if (m.isAnnotationPresent(DeleteMany.class)) {
                generateDeleteManyMethod(enhanceClass, m, databaseName, collectionName);
            }
        }
        return enhanceClass.toClass();
    }

    /**
     * 生成InsertOne代理方法
     */
    private static void generateInsertOneMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 1);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ com.lance.game.orm.MongoUtils.insertOne(\"%s\", \"%s\", $1, new com.lance.game.orm.handler.TestConfigDocumentHandler()); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 生成InsertMany代理方法
     */
    private static void generateInsertManyMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 1);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ com.lance.game.orm.MongoUtils.insertMany(\"%s\", \"%s\", $1, new com.lance.game.orm.handler.TestConfigDocumentHandler()); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 生成findOne代理方法
     */
    private static void generateFindOneMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 1);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ return (" + m.getReturnType().getName() + ") com.lance.game.orm.MongoUtils.findOne(\"%s\", \"%s\", $1, new com.lance.game.orm.handler.TestConfigDocumentHandler()); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 生成findMany代理方法
     */
    private static void generateFindManyMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 1);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ return com.lance.game.orm.MongoUtils.findMany(\"%s\", \"%s\", $1, new com.lance.game.orm.handler.TestConfigDocumentHandler()); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 生成count代理方法
     */
    private static void generateCountMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 1);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ return com.lance.game.orm.MongoUtils.count(\"%s\", \"%s\", $1); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 生成findOneAndReplace代理方法
     */
    private static void generateFindOneAndReplaceMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 2);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ com.lance.game.orm.MongoUtils.findOneAndReplace(\"%s\", \"%s\", $1, $2, new com.lance.game.orm.handler.TestConfigDocumentHandler()); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 生成deleteOne代理方法
     */
    private static void generateDeleteOneMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 1);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ com.lance.game.orm.MongoUtils.deleteOne(\"%s\", \"%s\", $1); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }


    /**
     * 生成deleteMany代理方法
     */
    private static void generateDeleteManyMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        checkMethodParameter(m, 1);

        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ com.lance.game.orm.MongoUtils.deleteMany(\"%s\", \"%s\", $1); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 方法参数校验
     *
     * @param requireCount 要求参数长度
     */
    private static void checkMethodParameter(Method m, int requireCount) {
        int parameterCount = m.getParameterCount();
        if (parameterCount != requireCount) {
            throw new IncorrectMethodParameterCountException(
                    "方法[" + m.getName() + "]参数长度不正确，要求长度为：" + requireCount + "，实际长度为：" + parameterCount);
        }
    }

    /**
     * 生成方法模板
     */
    private static CtMethod generateMethodTemplate(CtClass enhanceClass, Method m) throws NotFoundException {
        CtMethod ctMethod = new CtMethod(classPool.getCtClass(m.getReturnType().getName()), m.getName(), generateParameters(m), enhanceClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        return ctMethod;
    }

    /**
     * 生成方法参数
     */
    private static CtClass[] generateParameters(Method m) throws NotFoundException {
        Parameter[] parameters = m.getParameters();
        String[] classNames = new String[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            classNames[i] = parameters[i].getType().getName();
        }
        return classPool.get(classNames);
    }
}
