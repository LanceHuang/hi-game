package com.lance.game.orm;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

/**
 * 基本数据源
 *
 * @author Lance
 */
public class BasicMongoDataSource implements MongoDataSource {

    private String url;

    @Override
    public MongoClient getMongoClient() {
        return MongoClients.create(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
