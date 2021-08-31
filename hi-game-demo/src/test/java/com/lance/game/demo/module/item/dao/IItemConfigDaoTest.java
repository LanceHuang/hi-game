package com.lance.game.demo.module.item.dao;

import com.lance.common.tool.JsonUtils;
import com.lance.game.demo.module.item.config.ItemConfig;
import com.lance.game.mongodb.MongoDaoScanner;
import com.lance.game.mongodb.MongoDataSource;
import com.lance.game.mongodb.PooledMongoDataSource;
import com.lance.game.mongodb.runner.DefaultMongoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = IItemConfigDaoTest.class)
@Configuration
public class IItemConfigDaoTest {

    @Resource
    private IItemConfigDao itemConfigDao;

    @Bean(initMethod = "init", destroyMethod = "close")
    public PooledMongoDataSource mongoDataSource() {
        PooledMongoDataSource mongoDataSource = new PooledMongoDataSource();
        mongoDataSource.setConnectionString("mongodb://localhost:27017");
        mongoDataSource.setMaxActive(3);
        return mongoDataSource;
    }

    @Bean
    public DefaultMongoRunner defaultMongoRunner(MongoDataSource mongoDataSource) {
        return new DefaultMongoRunner(mongoDataSource);
    }

    @Bean
    public MongoDaoScanner mongoDaoScanner() {
        MongoDaoScanner mongoDaoScanner = new MongoDaoScanner();
        mongoDaoScanner.setBasePackage("com.lance.game.demo.module.item.dao");
        return mongoDaoScanner;
    }

    @Test
    public void addItemConfig() {
        String[] data = {
                "{\"id\": 1, \"name\": \"石头\", \"type\": 1}",
                "{\"id\": 2, \"name\": \"树叶\", \"type\": 1}",
                "{\"id\": 2001, \"name\": \"小型生命药水\", \"type\": 2, \"value\": {\"HP\": 100}}",
                "{\"id\": 2002, \"name\": \"中型生命药水\", \"type\": 2, \"value\": {\"HP\": 500}}",
                "{\"id\": 2003, \"name\": \"大型生命药水\", \"type\": 2, \"value\": {\"HP\": 1000}}",
                "{\"id\": 2004, \"name\": \"小型法力药水\", \"type\": 2, \"value\": {\"MP\": 50}}",
                "{\"id\": 3001, \"name\": \"新手刀\", \"type\": 3, \"pos\":1, \"attribute\": {\"ATK\": 100}}",
                "{\"id\": 3003, \"name\": \"新手鞋\", \"type\": 3, \"pos\":2, \"attribute\": {\"DEFENCE\": 50, \"HP\": 100}}"
        };

        for (String item : data) {
            itemConfigDao.addItemConfig(JsonUtils.json2object(item, ItemConfig.class));
        }
    }

    @Test
    public void getItemConfig() {
        String filter = "{id:2004}";

        ItemConfig itemConfig = itemConfigDao.getItemConfig(filter);
        System.out.println(itemConfig);
        System.out.println(itemConfig.getId());
        System.out.println(itemConfig.getName());
        System.out.println(itemConfig.getType());
    }

    @Test
    public void getItemConfigs() {
        List<ItemConfig> itemConfigs = itemConfigDao.getItemConfigs(null);
        itemConfigs.forEach(System.out::println);
    }

    @Test
    public void deleteMany() {
        getItemConfigs();
        System.out.println("=================================");
        itemConfigDao.deleteItemConfigs("{type:2,name:'大型生命药水'}");
        System.out.println("=================================");
        getItemConfigs();
    }

    @Test
    public void findOneAndReplace() {
        getItemConfigs();
        System.out.println("=================================");
        itemConfigDao.replaceItemConfig("{type:2}",
                JsonUtils.json2object("{\"id\": 2001, \"name\": \"小型生命药水\", \"type\": 2, \"value\": {\"HP\": 100}}", ItemConfig.class)
        );
        System.out.println("=================================");
        getItemConfigs();
    }

}