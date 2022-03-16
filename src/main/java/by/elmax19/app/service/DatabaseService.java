package by.elmax19.app.service;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {
    private final MongoDatabase database;

    public DatabaseService(
            @Value("${spring.data.mongodb.host}") String host,
            @Value("${spring.data.mongodb.port}") int port,
            @Value("${mongodb.database.name}") String databaseName) {
        MongoClient client = new MongoClient(host, port);
        database = client.getDatabase(databaseName);
    }


    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
}
