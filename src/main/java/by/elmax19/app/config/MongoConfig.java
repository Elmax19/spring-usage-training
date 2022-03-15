package by.elmax19.app.config;


import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {
    private static final String host = "localhost";
    private static final int port = 27017;

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(host, port);
    }
}
