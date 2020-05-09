package com.lance.game.orm.handler;

import com.lance.game.orm.DocumentHandler;
import com.lance.game.orm.model.TestConfig;
import org.bson.Document;

/**
 * @author Lance
 */
public class TestConfigDocumentHandler implements DocumentHandler<TestConfig> {

    @Override
    public Document parse(TestConfig data) {
        Document doc = new Document();
        doc.append("id", data.getId());
        doc.append("name", data.getName());
        doc.append("age", data.getAge());
        return doc;
    }

    @Override
    public TestConfig handle(Document doc) {
        TestConfig itemConfig = new TestConfig();
        itemConfig.setId(doc.get("id", 0));
        itemConfig.setName(doc.getString("name"));
        itemConfig.setAge(doc.get("age", 0));
        return itemConfig;
    }
}
