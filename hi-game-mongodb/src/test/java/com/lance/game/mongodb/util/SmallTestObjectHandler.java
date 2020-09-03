package com.lance.game.mongodb.util;

import com.lance.game.mongodb.handler.DocumentHandler;
import org.bson.Document;

/**
 * @author Lance
 */
public class SmallTestObjectHandler implements DocumentHandler<SmallTestObject> {

    @Override
    public Document parse(SmallTestObject data) {
        Document doc = new Document();
        doc.append("a1", data.getA1());
        doc.append("a2", data.getA2());
        doc.append("b1", data.getB1());
        doc.append("b2", data.getB2());
        doc.append("c1", data.getC1());
        doc.append("c2", data.getC2());
        return doc;
    }

    @Override
    public SmallTestObject handle(Document doc) {
        SmallTestObject obj = new SmallTestObject();
        obj.setA1(doc.getInteger("a1"));
        obj.setA2(doc.getInteger("a2"));
        obj.setB1(doc.getLong("b1"));
        obj.setB2(doc.getLong("b2"));
        obj.setC1(doc.getString("c1"));
        obj.setC2(doc.getString("c2"));
        return obj;
    }
}
