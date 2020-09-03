package com.lance.game.mongodb;

import com.mongodb.client.MongoClient;

/**
 * MongoDB连接池
 *
 * @author Lance
 */
public interface MongoDataSource {

    MongoClient getMongoClient();

}
