package com.lance.game.orm;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MongoDataSourceTest {

    private MongoDataSource mongoDataSource;

    @BeforeEach
    public void beforeEach() {
        this.mongoDataSource = new DefaultMongoDataSource();
    }

    @AfterEach
    public void afterEach() {
//        this.mongoDataSource
    }

    @Test
    void getMongoClient() {

    }
}