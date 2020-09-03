package com.lance.game.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * 基本数据源
 *
 * @author Lance
 */
public class BasicMongoDataSource implements MongoDataSource {

    private String connectionString;

    @Override
    public MongoClient getMongoClient() {
        return MongoClients.create(connectionString);
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}
