package com.lance.game.mongodb.util;

import com.lance.game.mongodb.handler.DocumentHandler;
import org.bson.Document;

/**
 * @author Lance
 */
public class LargeTestObjectHandler implements DocumentHandler<LargeTestObject> {

    @Override
    public Document parse(LargeTestObject data) {
        Document doc = new Document();
        doc.append("a1", data.getA1());
        doc.append("a2", data.getA2());
        doc.append("a3", data.getA3());
        doc.append("a4", data.getA4());
        doc.append("a5", data.getA5());
        doc.append("a6", data.getA6());
        doc.append("a7", data.getA7());
        doc.append("a8", data.getA8());
        doc.append("a9", data.getA9());
        doc.append("a10", data.getA10());
        doc.append("a11", data.getA11());
        doc.append("a12", data.getA12());
        doc.append("a13", data.getA13());
        doc.append("a14", data.getA14());
        doc.append("a15", data.getA15());
        doc.append("a16", data.getA16());
        doc.append("a17", data.getA17());
        doc.append("a18", data.getA18());
        doc.append("a19", data.getA19());
        doc.append("a20", data.getA20());
        doc.append("b1", data.getB1());
        doc.append("b2", data.getB2());
        doc.append("b3", data.getB3());
        doc.append("b4", data.getB4());
        doc.append("b5", data.getB5());
        doc.append("b6", data.getB6());
        doc.append("b7", data.getB7());
        doc.append("b8", data.getB8());
        doc.append("b9", data.getB9());
        doc.append("b10", data.getB10());
        doc.append("b11", data.getB11());
        doc.append("b12", data.getB12());
        doc.append("b13", data.getB13());
        doc.append("b14", data.getB14());
        doc.append("b15", data.getB15());
        doc.append("b16", data.getB16());
        doc.append("b17", data.getB17());
        doc.append("b18", data.getB18());
        doc.append("b19", data.getB19());
        doc.append("b20", data.getB20());
        doc.append("c1", data.getC1());
        doc.append("c2", data.getC2());
        doc.append("c3", data.getC3());
        doc.append("c4", data.getC4());
        doc.append("c5", data.getC5());
        doc.append("c6", data.getC6());
        doc.append("c7", data.getC7());
        doc.append("c8", data.getC8());
        doc.append("c9", data.getC9());
        doc.append("c10", data.getC10());
        doc.append("c11", data.getC11());
        doc.append("c12", data.getC12());
        doc.append("c13", data.getC13());
        doc.append("c14", data.getC14());
        doc.append("c15", data.getC15());
        doc.append("c16", data.getC16());
        doc.append("c17", data.getC17());
        doc.append("c18", data.getC18());
        doc.append("c19", data.getC19());
        doc.append("c20", data.getC20());
        return doc;
    }

    @Override
    public LargeTestObject handle(Document doc) {
        LargeTestObject obj = new LargeTestObject();
        obj.setA1(doc.getInteger("a1"));
        obj.setA2(doc.getInteger("a2"));
        obj.setA3(doc.getInteger("a3"));
        obj.setA4(doc.getInteger("a4"));
        obj.setA5(doc.getInteger("a5"));
        obj.setA6(doc.getInteger("a6"));
        obj.setA7(doc.getInteger("a7"));
        obj.setA8(doc.getInteger("a8"));
        obj.setA9(doc.getInteger("a9"));
        obj.setA10(doc.getInteger("a10"));
        obj.setA11(doc.getInteger("a11"));
        obj.setA12(doc.getInteger("a12"));
        obj.setA13(doc.getInteger("a13"));
        obj.setA14(doc.getInteger("a14"));
        obj.setA15(doc.getInteger("a15"));
        obj.setA16(doc.getInteger("a16"));
        obj.setA17(doc.getInteger("a17"));
        obj.setA18(doc.getInteger("a18"));
        obj.setA19(doc.getInteger("a19"));
        obj.setA20(doc.getInteger("a20"));
        obj.setB1(doc.getLong("b1"));
        obj.setB2(doc.getLong("b2"));
        obj.setB3(doc.getLong("b3"));
        obj.setB4(doc.getLong("b4"));
        obj.setB5(doc.getLong("b5"));
        obj.setB6(doc.getLong("b6"));
        obj.setB7(doc.getLong("b7"));
        obj.setB8(doc.getLong("b8"));
        obj.setB9(doc.getLong("b9"));
        obj.setB10(doc.getLong("b10"));
        obj.setB11(doc.getLong("b11"));
        obj.setB12(doc.getLong("b12"));
        obj.setB13(doc.getLong("b13"));
        obj.setB14(doc.getLong("b14"));
        obj.setB15(doc.getLong("b15"));
        obj.setB16(doc.getLong("b16"));
        obj.setB17(doc.getLong("b17"));
        obj.setB18(doc.getLong("b18"));
        obj.setB19(doc.getLong("b19"));
        obj.setB20(doc.getLong("b20"));
        obj.setC1(doc.getString("c1"));
        obj.setC2(doc.getString("c2"));
        obj.setC3(doc.getString("c3"));
        obj.setC4(doc.getString("c4"));
        obj.setC5(doc.getString("c5"));
        obj.setC6(doc.getString("c6"));
        obj.setC7(doc.getString("c7"));
        obj.setC8(doc.getString("c8"));
        obj.setC9(doc.getString("c9"));
        obj.setC10(doc.getString("c10"));
        obj.setC11(doc.getString("c11"));
        obj.setC12(doc.getString("c12"));
        obj.setC13(doc.getString("c13"));
        obj.setC14(doc.getString("c14"));
        obj.setC15(doc.getString("c15"));
        obj.setC16(doc.getString("c16"));
        obj.setC17(doc.getString("c17"));
        obj.setC18(doc.getString("c18"));
        obj.setC19(doc.getString("c19"));
        obj.setC20(doc.getString("c20"));
        return obj;
    }
}
