package com.lance.game.mongodb.dao;

import com.lance.game.mongodb.annotation.Count;
import com.lance.game.mongodb.annotation.DeleteMany;
import com.lance.game.mongodb.annotation.DeleteOne;
import com.lance.game.mongodb.annotation.FindMany;
import com.lance.game.mongodb.annotation.FindOne;
import com.lance.game.mongodb.annotation.FindOneAndReplace;
import com.lance.game.mongodb.annotation.InsertMany;
import com.lance.game.mongodb.annotation.InsertOne;
import com.lance.game.mongodb.annotation.MongoDao;
import com.lance.game.mongodb.model.TestConfig;

import java.util.List;

/**
 * @author Lance
 */
@MongoDao(databaseName = "db_mongo_orm", collectionName = "c_test_config", modelClass = TestConfig.class)
public interface INewTestConfigDao {

    @InsertOne
    void addTestConfig(TestConfig testConfig);

    @InsertMany
    void addTestConfigs(List<TestConfig> testConfigs);

    @FindOne
    TestConfig getTestConfig(String filter);

    @FindMany
    List<TestConfig> getTestConfigs(String filter);

    @Count
    long countTestConfig(String filter);

    @FindOneAndReplace
    void replaceTestConfig(String filter, TestConfig testConfig);

    @DeleteOne
    void deleteTestConfig(String filter);

    @DeleteMany
    void deleteTestConfigs(String filter);
}
