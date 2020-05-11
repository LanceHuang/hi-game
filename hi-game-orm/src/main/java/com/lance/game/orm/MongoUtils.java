package com.lance.game.orm;

import com.lance.game.orm.exception.TooManyResultException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lance
 */
public class MongoUtils {

    private static final String URL = "mongodb://localhost:27017";

    /** 最大批量插入文档数 */
    private static final int INSERT_BATCH_COUNT = 1000;

    private MongoUtils() {
    }

    public static MongoClient getClient() {
        // todo 不知道mongo有没有连接池
        return MongoClients.create(URL);
    }

    public static void close(MongoClient mongoClient) {
        if (mongoClient == null) {
            return;
        }
        mongoClient.close();
    }

    /**
     * 插入数据
     */
    public static <T> void insertOne(String databaseName, String collectionName, T data, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(documentHandler.parse(data));
        close(client);
    }

    /**
     * 批量插入数据
     */
    public static <T> void insertMany(String databaseName, String collectionName, List<T> list, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        List<Document> manyDocs = new LinkedList<>();
        int i = 0;
        for (T item : list) {
            manyDocs.add(documentHandler.parse(item));
            if (i % INSERT_BATCH_COUNT == 0) {
                collection.insertMany(manyDocs);
                manyDocs = new LinkedList<>();
            }
            i++;
        }
        if (!manyDocs.isEmpty()) {
            collection.insertMany(manyDocs);
        }

        close(client);
    }

    /**
     * 根据条件删除文档
     */
    public static void deleteOne(String databaseName, String collectionName, String filter) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.deleteOne(Document.parse(filter));
        close(client);
    }

    /**
     * 根据条件删除文档
     */
    public static void deleteMany(String databaseName, String collectionName, String filter) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.deleteMany(Document.parse(filter));
        close(client);
    }

    /**
     * 查询单个文档
     */
    public static <T> T findOne(String databaseName, String collectionName, String filter, DocumentHandler<T> documentHandler) {
        List<T> result = findMany(databaseName, collectionName, filter, documentHandler);
        if (result.size() == 1) {
            return result.get(0);
        } else if (result.size() > 1) {
            throw new TooManyResultException("太多返回结果：" + result.size());
        } else {
            return null;
        }
    }

    /**
     * 查询所有文档
     */
    public static <T> List<T> findMany(String databaseName, String collectionName, String filter, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        List<T> result = new LinkedList<>();
        FindIterable<Document> documents;
        if (filter == null) {
            documents = collection.find();
        } else {
            documents = collection.find(Document.parse(filter));
        }

        for (Document doc : documents) {
            result.add(documentHandler.handle(doc));
        }

        close(client);
        return result;
    }

    /**
     * 计数
     */
    public static long count(String databaseName, String collectionName, String filter) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        long result;
        if (filter == null) {
            result = collection.countDocuments();
        } else {
            result = collection.countDocuments(Document.parse(filter));
        }

        close(client);
        return result;
    }

    /**
     * 查询并替换文档
     */
    public static <T> void findOneAndReplace(String databaseName, String collectionName, String filter, T data, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.findOneAndReplace(Document.parse(filter), documentHandler.parse(data));
        close(client);
    }

    // todo findOneAndUpdate updateOne updateMany 方法签名不好设计，不想给调用者看到Mongo相关内容Bson

}
