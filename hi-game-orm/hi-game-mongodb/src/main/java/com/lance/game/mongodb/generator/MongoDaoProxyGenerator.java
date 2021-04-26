package com.lance.game.mongodb.generator;

import com.lance.game.mongodb.annotation.Count;
import com.lance.game.mongodb.annotation.DeleteMany;
import com.lance.game.mongodb.annotation.DeleteOne;
import com.lance.game.mongodb.annotation.FindMany;
import com.lance.game.mongodb.annotation.FindOne;
import com.lance.game.mongodb.annotation.FindOneAndReplace;
import com.lance.game.mongodb.annotation.InsertMany;
import com.lance.game.mongodb.annotation.InsertOne;
import com.lance.game.mongodb.annotation.MongoDao;
import com.lance.game.mongodb.runner.AbstractMongoRunner;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * DAO代理类生成工具，自动调用AbstractMongoRunner相应方法
 *
 * @author Lance
 */
public class MongoDaoProxyGenerator {

    private static final ClassPool classPool = ClassPool.getDefault();

    /**
     * 生成代理类
     *
     * @param clazz DAO接口
     */
    public static Class<?> generateProxyClass(Class<?> clazz) throws NotFoundException, CannotCompileException {
        if (clazz == null || !clazz.isInterface()) {
            throw new IllegalArgumentException("clazz 必须是接口");
        }

        MongoDao mongoDaoAnnotation = clazz.getDeclaredAnnotation(MongoDao.class);
        if (mongoDaoAnnotation == null) {
            throw new IllegalArgumentException("未标注MongoDao：" + clazz);
        }
        String databaseName = mongoDaoAnnotation.databaseName(); // 数据库名称
        String collectionName = mongoDaoAnnotation.collectionName(); // 集合名称
        Class<?> modelClass = mongoDaoAnnotation.modelClass(); // 模型类

        // 1. 生成子类
        CtClass enhanceClass = classPool.makeClass(clazz.getName() + "$Proxy");

        // 2. 实现接口
        enhanceClass.addInterface(classPool.getCtClass(clazz.getName()));

        // 3. 添加成员变量
        // Runner
        CtField runnerCtField = new CtField(classPool.getCtClass(AbstractMongoRunner.class.getName()), "runner", enhanceClass);
        runnerCtField.setModifiers(Modifier.PRIVATE);
        ClassFile classFile = enhanceClass.getClassFile();
        ConstPool constPool = classFile.getConstPool();
        AnnotationsAttribute runnerFieldAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation resourceAnnotation = new Annotation(Resource.class.getName(), constPool);
        runnerFieldAttr.addAnnotation(resourceAnnotation);
        runnerCtField.getFieldInfo().addAttribute(runnerFieldAttr);
        enhanceClass.addField(runnerCtField);

        // Handler
        Class<?> handlerClass = DocumentHandlerProxyGenerator.generateClass(modelClass);
        // 这种方式会默认调用 XXDocumentHandler$Proxy(this)，不推荐使用
//        CtClass handlerCtClass = classPool.getCtClass(handlerClass.getName());
//        CtField ctField = new CtField(handlerCtClass, "documentHandler", enhanceClass);
//        ctField.setModifiers(Modifier.PRIVATE);
//        enhanceClass.addField(ctField, CtField.Initializer.byNew(handlerCtClass));
        enhanceClass.addField(CtField.make(
                String.format("private %s documentHandler = new %s();", handlerClass.getName(), handlerClass.getName()), enhanceClass));

        // 4. 实现方法
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
                "{ runner.insertOne(\"%s\", \"%s\", $1, documentHandler); }",
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
                "{ runner.insertMany(\"%s\", \"%s\", $1, documentHandler); }",
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
                "{ return (" + m.getReturnType().getName() + ") runner.findOne(\"%s\", \"%s\", $1, documentHandler); }",
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
                "{ return runner.findMany(\"%s\", \"%s\", $1, documentHandler); }",
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
                "{ return runner.count(\"%s\", \"%s\", $1); }",
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
                "{ runner.findOneAndReplace(\"%s\", \"%s\", $1, $2, documentHandler); }",
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
                "{ runner.deleteOne(\"%s\", \"%s\", $1); }",
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
                "{ runner.deleteMany(\"%s\", \"%s\", $1); }",
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
            throw new IllegalArgumentException(
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
