package com.lance.game.mongodb.runner;

import com.lance.game.mongodb.PooledMongoDataSource;
import com.lance.game.mongodb.MongoDataSource;
import com.lance.game.mongodb.handler.DocumentHandler;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AbstractMongoRunnerTest.class)
@Configuration
public class AbstractMongoRunnerTest {

    @Resource
    private AbstractMongoRunner mongoRunner;

    @Bean(initMethod = "init", destroyMethod = "close")
    public PooledMongoDataSource mongoDataSource() {
        PooledMongoDataSource mongoDataSource = new PooledMongoDataSource();
        mongoDataSource.setConnectionString("mongodb://localhost:27017");
        mongoDataSource.setMaxActive(3);
        return mongoDataSource;
    }

    @Bean
    public AbstractMongoRunner mongoRunner(MongoDataSource mongoDataSource) {
        DefaultMongoRunner runner = new DefaultMongoRunner(mongoDataSource);
        runner.setBatchCount(50);
        return runner;
    }

    @Test
    public void getMongoClient() {
        mongoRunner.findOne("db_game", "c_item", "{id:1}", new DocumentHandler<Object>() {
            @Override
            public Document parse(Object data) {
                System.out.println(data);
                return null;
            }

            @Override
            public Object handle(Document doc) {
                System.out.println(doc);
                return null;
            }
        });
    }

}