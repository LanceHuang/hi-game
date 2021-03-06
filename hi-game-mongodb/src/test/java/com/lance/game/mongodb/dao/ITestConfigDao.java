package com.lance.game.mongodb.dao;

import com.lance.game.mongodb.model.TestConfig;

import java.util.List;

/**
 * @author Lance
 */
public interface ITestConfigDao {

    void addTestConfig(TestConfig testConfig);

    void addTestConfigs(List<TestConfig> testConfigs);

    TestConfig getTestConfigById(int id);

    List<TestConfig> getAllTestConfig();

    void replaceTestConfig(int id, TestConfig testConfig);

    void deleteTestConfig(int id);
}
