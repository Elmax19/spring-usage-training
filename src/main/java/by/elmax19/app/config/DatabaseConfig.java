package by.elmax19.app.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@Configuration
public class DatabaseConfig {
    private final MongoDatabase database;
    private final MongoClient client;

    public DatabaseConfig(
            @Value("${mongodb.database.host}") String host,
            @Value("${mongodb.database.port}") int port,
            @Value("${mongodb.database.name}") String databaseName) {
        client = new MongoClient(host, port);
        database = client.getDatabase(databaseName);
    }

    @Bean
    public MongoCollection<Document> playersCollection(
            @Value("${mongodb.database.collections.players-collection.name}") String collectionName) {
        return database.getCollection(collectionName);
    }

    @PreDestroy
    public void closeClient() {
        client.close();
    }
}
