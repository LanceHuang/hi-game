package com.lance.common.util;

import org.bson.Document;

/**
 * 参考common-dbutils的ResultSetHandler
 *
 * @author Lance
 */
public interface ResultHandler<T> {

    T handle(Document doc);
}
