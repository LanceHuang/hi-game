//package com.lance.common.util;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @author Lance
// */
//public class MongoReadBenchTest {
//
//    private int n = 1000;
//
//    @Test
//    public void testDBBatchRead() throws SQLException, ClassNotFoundException {
//        SmallTestObject smallTestObject = createSmallTestObject();
////        LargeTestObject largeTestObject = createLargeTestObject();
//
//        Class.forName("com.mysql.jdbc.Driver");
//        long t1 = System.currentTimeMillis();
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/common", "root", "123456");
//        conn.setAutoCommit(false);
//        PreparedStatement stat = conn.prepareStatement("insert into test(a1,a2,b1,b2,c1,c2) values(?,?,?,?,?,?)");
//        for (int i = 0; i < n; i++) {
//            stat.setInt(1, smallTestObject.getA1());
//            stat.setInt(2, smallTestObject.getA2());
//            stat.setLong(3, smallTestObject.getB1());
//            stat.setLong(4, smallTestObject.getB2());
//            stat.setString(5, smallTestObject.getC1());
//            stat.setString(6, smallTestObject.getC2());
//            stat.addBatch();
//            if (i % 1000 == 0) {
//                stat.executeBatch();
//            }
//        }
//        stat.executeBatch();
//        conn.commit();
//        stat.close();
//        conn.close();
//        long t2 = System.currentTimeMillis();
//        System.out.println("MySQL耗时：" + (t2 - t1));
//    }
//
//    @Test
//    public void testMongoRead() {
//        SmallTestObjectHandler smallhandler = new SmallTestObjectHandler();
//
//        long t1 = System.currentTimeMillis();
//        MongoClient client = MongoUtils.getClient();
//        MongoDatabase database = client.getDatabase("common");
//        MongoCollection<Document> collection = database.getCollection("test");
//        List<Document> manyDocs = new LinkedList<>();
//        for (int i = 0; i < n; i++) {
//            manyDocs.add(smallhandler.parse(smallTestObject));
//            if (i % 1000 == 0) {
//                collection.insertMany(manyDocs);
//                manyDocs = new LinkedList<>();
//            }
////            collection.insertOne(smallhandler.parse(smallTestObject));
//        }
//        collection.insertMany(manyDocs);
//        MongoUtils.close(client);
//        long t2 = System.currentTimeMillis();
//        System.out.println("MongoDB耗时：" + (t2 - t1));
//    }
//
//
//}
