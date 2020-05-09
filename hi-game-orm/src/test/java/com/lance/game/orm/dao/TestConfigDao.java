package com.lance.game.orm.dao;

import com.lance.game.orm.MongoUtils;
import com.lance.game.orm.handler.TestConfigDocumentHandler;
import com.lance.game.orm.model.TestConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lance
 */
@Repository
public class TestConfigDao implements ITestConfigDao {

    private String databaseName = "test-orm";
    private String collectionName = "testConfig";

    @Override
    public void saveTestConfig(TestConfig testConfig) {
        MongoUtils.insert(databaseName, collectionName, testConfig, new TestConfigDocumentHandler());
    }

    @Override
    public TestConfig getTestConfigById(int id) {
        return MongoUtils.findOne(databaseName, collectionName, "{id:" + id + "}", new TestConfigDocumentHandler());
    }

    @Override
    public List<TestConfig> getAllTestConfig() {
        return MongoUtils.find(databaseName, collectionName, new TestConfigDocumentHandler());
    }
}
