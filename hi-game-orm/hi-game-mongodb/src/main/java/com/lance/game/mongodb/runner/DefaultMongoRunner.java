package com.lance.game.mongodb.runner;

import com.lance.game.mongodb.MongoDataSource;
import com.mongodb.client.MongoClient;

/**
 * @author Lance
 */
public class DefaultMongoRunner extends AbstractMongoRunner {

    protected MongoDataSource mongoDataSource;

    public DefaultMongoRunner(MongoDataSource mongoDataSource) {
        this.mongoDataSource = mongoDataSource;
    }

    @Override
    public MongoClient getClient() {
        if (this.mongoDataSource == null) {
            throw new IllegalStateException("mongoDataSource为null");
        }
        return this.mongoDataSource.getMongoClient();
    }
}
