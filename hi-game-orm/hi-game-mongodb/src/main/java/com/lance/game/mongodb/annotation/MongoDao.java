package com.lance.game.mongodb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lance
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MongoDao {

    /**
     * 数据库名称
     */
    String databaseName();

    /**
     * 集合名称
     */
    String collectionName();

    /**
     * 模型类
     */
    Class<?> modelClass();
}
