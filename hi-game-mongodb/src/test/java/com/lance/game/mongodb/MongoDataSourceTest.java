package com.lance.game.mongodb;

import com.mongodb.client.MongoClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MongoDataSourceTest {

    private PooledMongoDataSource mongoDataSource;

    @BeforeEach
    public void beforeEach() {
        PooledMongoDataSource mds = new PooledMongoDataSource();
        mds.setConnectionString("mongodb://localhost:27017");
        mds.setMaxActive(3);
        this.mongoDataSource = mds;
    }

    @AfterEach
    public void afterEach() throws IOException {
        if (this.mongoDataSource != null) {
            this.mongoDataSource.close();
        }
    }

    @Test
    public void getMongoClient() {
        MongoClient mongoClient = this.mongoDataSource.getMongoClient();
        System.out.println(mongoClient);
        mongoClient.close();
    }

    @Test
    public void testMaxActive() {
        // maxActive=3
        MongoClient mongoClient = this.mongoDataSource.getMongoClient();
        Assertions.assertNotNull(mongoClient); // activeCount=1

        mongoClient = this.mongoDataSource.getMongoClient();
        Assertions.assertNotNull(mongoClient); // activeCount=2
        mongoClient.close();

        mongoClient = this.mongoDataSource.getMongoClient();
        Assertions.assertNotNull(mongoClient); // activeCount=2
        mongoClient.close();

        mongoClient = this.mongoDataSource.getMongoClient();
//        Assertions.assertNull(mongoClient);
    }
}