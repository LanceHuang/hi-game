package com.lance.game.orm;

import com.mongodb.client.MongoClient;

/**
 * @author Lance
 */
public interface MongoDataSource {

    MongoClient getMongoClient();

}
