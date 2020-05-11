package com.lance.game.orm;

import com.lance.game.orm.annotation.InsertOne;
import com.lance.game.orm.annotation.MongoDao;
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
            }
        }
        return enhanceClass.toClass();
    }

    /**
     * 生成InsertOne代理方法
     */
    private static void generateInsertOneMethod(CtClass enhanceClass, Method m, String databaseName, String collectionName) throws NotFoundException, CannotCompileException {
        CtMethod ctMethod = generateMethodTemplate(enhanceClass, m);
        String methodBody = String.format(
                "{ com.lance.game.orm.MongoUtils.insertOne(\"%s\", \"%s\", $1, new com.lance.game.orm.handler.TestConfigDocumentHandler()); }",
                databaseName, collectionName);
        ctMethod.setBody(methodBody);

        enhanceClass.addMethod(ctMethod);
    }

    /**
     * 生成方法模板
     */
    private static CtMethod generateMethodTemplate(CtClass enhanceClass, Method m) throws NotFoundException {
        // todo 返回值要适配
        CtMethod ctMethod = new CtMethod(CtClass.voidType, m.getName(), generateParameters(m), enhanceClass);
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
