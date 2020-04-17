package com.lance.common.util;

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
    public static <T> void insert(String databaseName, String collectionName, T data, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(documentHandler.parse(data));
        close(client);
    }

    /**
     * 查询单个文档
     */
    public static <T> T findOne(String databaseName, String collectionName, String filter, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        T result = null;
        for (Document doc : collection.find(Document.parse(filter))) {
            result = documentHandler.handle(doc);
            break;
        }
        collection.insertOne(new Document());

        close(client);
        return result;
    }

    /**
     * 查询所有文档
     */
    public static <T> List<T> find(String databaseName, String collectionName, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        List<T> result = new LinkedList<>();
        for (Document doc : collection.find()) {
            result.add(documentHandler.handle(doc));
        }


        close(client);
        return result;
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
     * 查询并替换文档
     */
    public static <T> void findOneAndReplace(String databaseName, String collectionName, String filter, T data, DocumentHandler<T> documentHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        collection.findOneAndReplace(Document.parse(filter), documentHandler.parse(data));
        close(client);
    }

}
