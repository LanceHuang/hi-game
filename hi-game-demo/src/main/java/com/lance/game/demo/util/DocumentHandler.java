package com.lance.game.demo.util;

import org.bson.Document;

/**
 * 参考common-dbutils的ResultSetHandler
 *
 * @author Lance
 */
public interface DocumentHandler<T> {

    /**
     * 将数据转换成文档
     */
    Document parse(T data);

    /**
     * 将文档解析成数据
     */
    T handle(Document doc);
}
