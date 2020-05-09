package com.lance.game.orm;

import com.mongodb.client.MongoClient;

import java.util.LinkedList;

/**
 * @author Lance
 */
public class DefaultMongoDataSource implements MongoDataSource {

    private LinkedList<MongoClient> pool = new LinkedList<>();

    private int maxConnection = 10;

    @Override
    public MongoClient getMongoClient() {
        if (this.pool.size() <= 0) {

        }
        return pool.pop();
    }
}
