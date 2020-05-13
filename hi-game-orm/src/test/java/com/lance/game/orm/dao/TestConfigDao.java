package com.lance.game.orm.dao;

import com.lance.game.orm.util.MongoUtils;
import com.lance.game.orm.handler.TestConfigDocumentHandler;
import com.lance.game.orm.model.TestConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lance
 */
@Repository
public class TestConfigDao implements ITestConfigDao {

    private String databaseName = "db_mongo_orm";
    private String collectionName = "c_test_config";

    @Override
    public void addTestConfig(TestConfig testConfig) {
        MongoUtils.insertOne(databaseName, collectionName, testConfig, new TestConfigDocumentHandler());
    }

    @Override
    public void addTestConfigs(List<TestConfig> testConfigs) {
        MongoUtils.insertMany(databaseName, collectionName, testConfigs, new TestConfigDocumentHandler());
    }

    @Override
    public TestConfig getTestConfigById(int id) {
        return MongoUtils.findOne(databaseName, collectionName, "{id:" + id + "}", new TestConfigDocumentHandler());
    }

    @Override
    public List<TestConfig> getAllTestConfig() {
        return MongoUtils.findMany(databaseName, collectionName, null, new TestConfigDocumentHandler());
    }

    @Override
    public void replaceTestConfig(int id, TestConfig testConfig) {
        MongoUtils.findOneAndReplace(databaseName, collectionName, "{id:" + id + "}", testConfig, new TestConfigDocumentHandler());
    }

    @Override
    public void deleteTestConfig(int id) {
        MongoUtils.deleteOne(databaseName, collectionName, "{id:" + id + "}");
    }
}
