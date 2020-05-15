package com.lance.game.orm.runner;

import com.lance.game.orm.DefaultMongoDataSource;
import com.lance.game.orm.MongoDataSource;
import com.lance.game.orm.handler.DocumentHandler;
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
    public DefaultMongoDataSource mongoDataSource() {
        DefaultMongoDataSource mongoDataSource = new DefaultMongoDataSource();
        mongoDataSource.setUrl("mongodb://localhost:27017");
        mongoDataSource.setMaxActive(3);
        return mongoDataSource;
    }

    @Bean
    public AbstractMongoRunner mongoRunner(MongoDataSource mongoDataSource) {
        PooledMongoRunner runner = new PooledMongoRunner(mongoDataSource);
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