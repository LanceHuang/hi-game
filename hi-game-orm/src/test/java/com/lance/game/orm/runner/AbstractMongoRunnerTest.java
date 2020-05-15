package com.lance.game.orm.runner;

import com.lance.game.orm.DefaultMongoDataSource;
import com.lance.game.orm.handler.DocumentHandler;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbstractMongoRunnerTest {

    private AbstractMongoRunner mongoRunner;

    @BeforeEach
    public void beforeEach() {
        DefaultMongoDataSource mongoDataSource = new DefaultMongoDataSource();
        mongoDataSource.setUrl("mongodb://localhost:27017");
        this.mongoRunner = new DefaultMongoRunner(mongoDataSource);
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