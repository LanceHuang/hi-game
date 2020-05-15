package com.lance.game.orm.runner;

import com.lance.game.orm.MongoDataSource;
import com.mongodb.client.MongoClient;

/**
 * @author Lance
 */
public class PooledMongoRunner extends AbstractMongoRunner {

    protected MongoDataSource mongoDataSource;

    public PooledMongoRunner(MongoDataSource mongoDataSource) {
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
