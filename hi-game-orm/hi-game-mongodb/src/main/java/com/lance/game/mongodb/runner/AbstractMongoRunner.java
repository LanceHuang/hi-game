package com.lance.game.mongodb.runner;

import com.lance.game.mongodb.exception.TooManyResultException;
import com.lance.game.mongodb.handler.DocumentHandler;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

/**
 * mongo逻辑处理器
 *
 * @author Lance
 */
public abstract class AbstractMongoRunner {

    public static final int DEFAULT_BATCH_COUNT = 100;

    /** 最大批量插入文档数 */
    protected int batchCount = DEFAULT_BATCH_COUNT;

    protected abstract MongoClient getClient();

    /**
     * 插入数据
     */
    public <T> void insertOne(String databaseName, String collectionName, T data, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(documentHandler.parse(data));
        client.close();
    }

    /**
     * 批量插入数据
     */
    public <T> void insertMany(String databaseName, String collectionName, List<T> list, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        List<Document> manyDocs = new LinkedList<>();
        int i = 0;
        for (T item : list) {
            manyDocs.add(documentHandler.parse(item));
            if (i % this.batchCount == 0) {
                collection.insertMany(manyDocs);
                manyDocs = new LinkedList<>();
            }
            i++;
        }
        if (!manyDocs.isEmpty()) {
            collection.insertMany(manyDocs);
        }

        client.close();
    }

    /**
     * 根据条件删除文档
     */
    public void deleteOne(String databaseName, String collectionName, String filter) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.deleteOne(Document.parse(filter));
        client.close();
    }

    /**
     * 根据条件删除文档
     */
    public void deleteMany(String databaseName, String collectionName, String filter) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.deleteMany(Document.parse(filter));
        client.close();
    }

    /**
     * 查询单个文档
     */
    public <T> T findOne(String databaseName, String collectionName, String filter, DocumentHandler<T> documentHandler) {
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
    public <T> List<T> findMany(String databaseName, String collectionName, String filter, DocumentHandler<T> documentHandler) {
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

        client.close();
        return result;
    }

    /**
     * 计数
     */
    public long count(String databaseName, String collectionName, String filter) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        long result;
        if (filter == null) {
            result = collection.countDocuments();
        } else {
            result = collection.countDocuments(Document.parse(filter));
        }

        client.close();
        return result;
    }

    /**
     * 查询并替换文档
     */
    public <T> void findOneAndReplace(String databaseName, String collectionName, String filter, T data, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.findOneAndReplace(Document.parse(filter), documentHandler.parse(data));
        client.close();
    }

    public void setBatchCount(int batchCount) {
        this.batchCount = batchCount;
    }
}
