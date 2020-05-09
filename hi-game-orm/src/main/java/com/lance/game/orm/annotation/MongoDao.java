package com.lance.game.orm.annotation;

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

    // todo handler? 或者自动映射
}
