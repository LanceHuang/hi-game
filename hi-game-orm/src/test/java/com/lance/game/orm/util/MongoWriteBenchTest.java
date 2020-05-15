package com.lance.game.orm.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * 压测（不得不说，创建测试环境，都快要了我半条命）
 *
 * @author Lance
 */
public class MongoWriteBenchTest {

    private int n = 1000;


    @Test
    public void test() throws SQLException, ClassNotFoundException {
        System.out.println("======================= 10000 ========================");
        n = 1000;
        testDBBatchWrite();
        testMongoWrite();
        System.out.println("======================= 100000 =======================");
        n = 10000;
        testDBBatchWrite();
        testMongoWrite();
        System.out.println("======================= 1000000 ======================");
        n = 100000;
        testDBBatchWrite();
        testMongoWrite();
    }

//    @Test
//    public void testDBWrite() throws SQLException, ClassNotFoundException {
//        SmallTestObject smallTestObject = createSmallTestObject();
//        LargeTestObject largeTestObject = createLargeTestObject();
//
//        Class.forName("com.mysql.jdbc.Driver");
//        long t1 = System.currentTimeMillis();
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/common", "root", "123456");
//        PreparedStatement stat = conn.prepareStatement("insert into test(a1,a2,b1,b2,c1,c2) values(?,?,?,?,?,?)");
//        for (int i = 0; i < n; i++) {
//            stat.setInt(1, smallTestObject.getA1());
//            stat.setInt(2, smallTestObject.getA2());
//            stat.setLong(3, smallTestObject.getB1());
//            stat.setLong(4, smallTestObject.getB2());
//            stat.setString(5, smallTestObject.getC1());
//            stat.setString(6, smallTestObject.getC2());
//            stat.execute();
//        }
//        stat.close();
//        conn.close();
//        long t2 = System.currentTimeMillis();
//        System.out.println("MySQL耗时：" + (t2 - t1));
//    }


    @Test
    public void testDBBatchWrite() throws SQLException, ClassNotFoundException {
        SmallTestObject smallTestObject = createSmallTestObject();
//        LargeTestObject largeTestObject = createLargeTestObject();

        Class.forName("com.mysql.jdbc.Driver");
        long t1 = System.currentTimeMillis();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/common", "root", "123456");
        conn.setAutoCommit(false);
        PreparedStatement stat = conn.prepareStatement("insert into test(a1,a2,b1,b2,c1,c2) values(?,?,?,?,?,?)");
        for (int i = 0; i < n; i++) {
            stat.setInt(1, smallTestObject.getA1());
            stat.setInt(2, smallTestObject.getA2());
            stat.setLong(3, smallTestObject.getB1());
            stat.setLong(4, smallTestObject.getB2());
            stat.setString(5, smallTestObject.getC1());
            stat.setString(6, smallTestObject.getC2());
            stat.addBatch();
            if (i % 1000 == 0) {
                stat.executeBatch();
            }
        }
        stat.executeBatch();
        conn.commit();
        stat.close();
        conn.close();
        long t2 = System.currentTimeMillis();
        System.out.println("MySQL耗时：" + (t2 - t1));
    }

    @Test
    public void testMongoWrite() {
        SmallTestObjectHandler smallhandler = new SmallTestObjectHandler();
//        LargeTestObjectHandler largeHandler = new LargeTestObjectHandler();
        SmallTestObject smallTestObject = createSmallTestObject();
//        LargeTestObject largeTestObject = createLargeTestObject();

        long t1 = System.currentTimeMillis();
        MongoClient client = MongoUtils.getClient();
        MongoDatabase database = client.getDatabase("common");
        MongoCollection<Document> collection = database.getCollection("test");
        List<Document> manyDocs = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            manyDocs.add(smallhandler.parse(smallTestObject));
            if (i % 1000 == 0) {
                collection.insertMany(manyDocs);
                manyDocs = new LinkedList<>();
            }
//            collection.insertOne(smallhandler.parse(smallTestObject));
        }
        collection.insertMany(manyDocs);
        MongoUtils.close(client);
        long t2 = System.currentTimeMillis();
        System.out.println("MongoDB耗时：" + (t2 - t1));
    }

    private SmallTestObject createSmallTestObject() {
        SmallTestObject obj = new SmallTestObject();
        obj.setA1(1);
        obj.setA2(2);
        obj.setB1(1);
        obj.setB2(2);
        obj.setC1("1");
        obj.setC2("2");
        return obj;
    }

    private LargeTestObject createLargeTestObject() {
        LargeTestObject obj = new LargeTestObject();
        obj.setA1(1);
        obj.setA2(2);
        obj.setA3(3);
        obj.setA4(4);
        obj.setA5(5);
        obj.setA6(6);
        obj.setA7(7);
        obj.setA8(8);
        obj.setA9(9);
        obj.setA10(10);
        obj.setA11(11);
        obj.setA12(12);
        obj.setA13(13);
        obj.setA14(14);
        obj.setA15(15);
        obj.setA16(16);
        obj.setA17(17);
        obj.setA18(18);
        obj.setA19(19);
        obj.setA20(20);
        obj.setB1(1);
        obj.setB2(2);
        obj.setB3(3);
        obj.setB4(4);
        obj.setB5(5);
        obj.setB6(6);
        obj.setB7(7);
        obj.setB8(8);
        obj.setB9(9);
        obj.setB10(10);
        obj.setB11(11);
        obj.setB12(12);
        obj.setB13(13);
        obj.setB14(14);
        obj.setB15(15);
        obj.setB16(16);
        obj.setB17(17);
        obj.setB18(18);
        obj.setB19(19);
        obj.setB20(20);
        obj.setC1("1");
        obj.setC2("2");
        obj.setC3("3");
        obj.setC4("4");
        obj.setC5("5");
        obj.setC6("6");
        obj.setC7("7");
        obj.setC8("8");
        obj.setC9("9");
        obj.setC10("10");
        obj.setC11("11");
        obj.setC12("12");
        obj.setC13("13");
        obj.setC14("14");
        obj.setC15("15");
        obj.setC16("16");
        obj.setC17("17");
        obj.setC18("18");
        obj.setC19("19");
        obj.setC20("20");
        return obj;
    }


}
