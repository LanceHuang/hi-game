package com.lance.common.util;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

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
    public static void insert(String databaseName, String collectionName, String data) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(Document.parse(data));
        close(client);
    }

    public static <T> T findOne(String databaseName, String collectionName, String query, ResultHandler<T> resultHandler) {
        MongoClient client = getClient();
        MongoDatabase database = client.getDatabase(databaseName);
        MongoCollection<Document> collection = database.getCollection(collectionName);

        T result = null;
        for (Document doc : collection.find(Document.parse(query))) {
            result = resultHandler.handle(doc);
            break;
        }

        close(client);
        return result;
    }

}
