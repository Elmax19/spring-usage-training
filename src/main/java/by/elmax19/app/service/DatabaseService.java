package by.elmax19.app.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {
    private final MongoDatabase database;

    public DatabaseService(
            @Value("${mongodb.database.connection}") String connectionString,
            @Value("${mongodb.database.name}") String databaseName) {
        MongoClient client = MongoClients.create(connectionString);
        database = client.getDatabase(databaseName);
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
}
