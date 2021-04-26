package com.lance.game.mongodb;

import com.mongodb.ClientSessionOptions;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.connection.ClusterDescription;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

/**
 * MongoClient包装器
 *
 * @author Lance
 */
public class MongoClientWrapper implements MongoClient {

    private MongoClient mongoClient;

    private PooledMongoDataSource mongoDataSource;

    private volatile boolean close;

    public MongoClientWrapper(MongoClient mongoClient, PooledMongoDataSource mongoDataSource) {
        this.mongoClient = mongoClient;
        this.mongoDataSource = mongoDataSource;
    }

    @Override
    public MongoDatabase getDatabase(String databaseName) {
        checkState();

        return this.mongoClient.getDatabase(databaseName);
    }

    @Override
    public ClientSession startSession() {
        checkState();

        return this.mongoClient.startSession();
    }

    @Override
    public ClientSession startSession(ClientSessionOptions options) {
        checkState();

        return this.mongoClient.startSession(options);
    }

    @Override
    public void close() {
        if (!this.close) {
            synchronized (this) {
                if (!this.close) {
                    this.close = true;
                } else {
                    return;
                }
            }
        } else {
            return;
        }
        this.mongoDataSource.recycle(this);

        this.mongoClient = null;
        this.mongoDataSource = null;
    }

    @Override
    public MongoIterable<String> listDatabaseNames() {
        checkState();

        return this.mongoClient.listDatabaseNames();
    }

    @Override
    public MongoIterable<String> listDatabaseNames(ClientSession clientSession) {
        checkState();

        return this.mongoClient.listDatabaseNames(clientSession);
    }

    @Override
    public ListDatabasesIterable<Document> listDatabases() {
        checkState();

        return this.mongoClient.listDatabases();
    }

    @Override
    public ListDatabasesIterable<Document> listDatabases(ClientSession clientSession) {
        checkState();

        return this.mongoClient.listDatabases(clientSession);
    }

    @Override
    public <TResult> ListDatabasesIterable<TResult> listDatabases(Class<TResult> tResultClass) {
        checkState();

        return this.mongoClient.listDatabases(tResultClass);
    }

    @Override
    public <TResult> ListDatabasesIterable<TResult> listDatabases(ClientSession clientSession, Class<TResult> tResultClass) {
        checkState();

        return this.mongoClient.listDatabases(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch() {
        checkState();

        return this.mongoClient.watch();
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> tResultClass) {
        checkState();

        return this.mongoClient.watch(tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
        checkState();

        return this.mongoClient.watch(pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        checkState();

        return this.mongoClient.watch(pipeline, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
        checkState();

        return this.mongoClient.watch(clientSession);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, Class<TResult> tResultClass) {
        checkState();

        return this.mongoClient.watch(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession, List<? extends Bson> pipeline) {
        checkState();

        return this.mongoClient.watch(clientSession, pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession, List<? extends Bson> pipeline, Class<TResult> tResultClass) {
        checkState();

        return this.mongoClient.watch(clientSession, pipeline, tResultClass);
    }

    @Override
    public ClusterDescription getClusterDescription() {
        checkState();

        return this.mongoClient.getClusterDescription();
    }

    MongoClient getMongoClient() {
        return mongoClient;
    }

    private void checkState() {
        if (this.close) {
            throw new IllegalStateException("连接已关闭");
        }

        if (this.mongoClient == null) {
            throw new IllegalStateException("连接为null");
        }
    }

}
