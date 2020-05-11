package com.lance.game.orm.dao;

import com.lance.game.orm.annotation.Count;
import com.lance.game.orm.annotation.DeleteMany;
import com.lance.game.orm.annotation.DeleteOne;
import com.lance.game.orm.annotation.FindMany;
import com.lance.game.orm.annotation.FindOne;
import com.lance.game.orm.annotation.FindOneAndReplace;
import com.lance.game.orm.annotation.InsertMany;
import com.lance.game.orm.annotation.InsertOne;
import com.lance.game.orm.annotation.MongoDao;
import com.lance.game.orm.model.TestConfig;

import java.util.List;

/**
 * @author Lance
 */
@MongoDao(databaseName = "db_mongo_orm", collectionName = "c_test_config")
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
