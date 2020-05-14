package com.lance.game.orm;

import com.lance.game.orm.util.MongoUtils;
import com.mongodb.client.MongoClient;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @author Lance
 */
public class DefaultMongoDataSource implements MongoDataSource, Closeable {

//    private LinkedList<MongoClient> pool = new LinkedList<>();
//
//    private int maxConnection = 10;

    @Override
    public MongoClient getMongoClient() {
        return MongoUtils.getClient();
    }

    @Override
    public void close() throws IOException {

    }
}
