package com.lance.game.orm.dao;

import com.lance.game.orm.annotation.Find;
import com.lance.game.orm.annotation.FindOne;
import com.lance.game.orm.annotation.Insert;
import com.lance.game.orm.annotation.MongoDao;
import com.lance.game.orm.annotation.UpdateOne;
import com.lance.game.orm.model.TestConfig;

import java.util.List;

/**
 * @author Lance
 */
@MongoDao(databaseName = "test-orm", collectionName = "test-config")
public interface ITestConfigDao {

    @Insert
    void saveTestConfig(TestConfig testConfig);

    @FindOne
    TestConfig getTestConfigById(int id);

    @Find
    List<TestConfig> getAllTestConfig();

//    @UpdateOne


}
