package com.lance.game.orm;

import com.mongodb.client.MongoClient;

/**
 * MongoDB连接池
 *
 * @author Lance
 */
public interface MongoDataSource {

    MongoClient getMongoClient();

}
